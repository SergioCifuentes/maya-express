package com.mayaexpress.service;

import com.mayaexpress.dto.request.*;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.mayaexpress.dto.request.PackageDTO;
import com.mayaexpress.dto.request.ShipmentDTO;

import com.mayaexpress.dto.response.GuideHistoryDTO;
import com.mayaexpress.dto.response.MovementsByRegionDTO;
import com.mayaexpress.dto.response.ShipmentHistoryDTO;
import com.mayaexpress.entity.*;
import com.mayaexpress.entity.Package;
import com.mayaexpress.exception.APIException;
import com.mayaexpress.exception.ResourceNotFoundException;
import com.mayaexpress.repository.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ShipmentService {
    private final DestinationRepository destinationRepository;


    private final ShipmentRepository shipmentRepository;

    private final PackageRepository packageRepository;

    private final EconomicService economicService;


    private final WarehouseRepository warehouseRepository;

    private final ShipmentHistoryRepository shipmentHistoryRepository;

    private final ShipmentTripRepository shipmentTripRepository;

    private final RouteService routeService;


    @Value("${url-maya-express-qr}")
    private String urlLocalize;


    @Value("${qr.height}")
    private int height;

    @Value("${qr.width}")
    private int width;

    public ShipmentService(DestinationRepository destinationRepository, EconomicService economicService,
                            WarehouseRepository warehouseRepository,
                           ShipmentRepository shipmentRepository, PackageRepository packageRepository,
                           ShipmentHistoryRepository shipmentHistoryRepository, ShipmentTripRepository shipmentTripRepository,
                           RouteService routeService) {
        this.destinationRepository = destinationRepository;
        this.warehouseRepository=warehouseRepository;
        this.shipmentRepository=shipmentRepository;
        this.packageRepository=packageRepository;
        this.economicService=economicService;
        this.shipmentHistoryRepository=shipmentHistoryRepository;
        this.shipmentTripRepository= shipmentTripRepository;
        this.routeService=routeService ;
    }



    public List<Department> getDepartments(){
        return destinationRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }



    public Department getDepartment(Integer id){
        Optional<Department> department = destinationRepository.findById(id);
        if (department.isEmpty()) throw new ResourceNotFoundException("Department","id",id);
        return department.get();
    }

    public List<Price> getPrices(){
        return economicService.getPrices();
    }

    public Price getPrice(Integer id){
        return economicService.getPrice(id);
    }

    public Price getPriceByDepartments(Integer idOrigin, Integer idDestination){
        return economicService.getPriceByDepartments(idOrigin,idDestination);
    }

    public Price updatePrice(Price price, Integer id){
        return economicService.updatePrice(price,id);

    }

    public Shipment send(ShipmentDTO shipmentDTO){
        if(shipmentDTO.getPackages()==null || shipmentDTO.getPackages().length==0)throw new APIException(HttpStatus.BAD_REQUEST,"shipment must have packages");
        Warehouse warehouse= getWarehouse(shipmentDTO.getBranchId());
        Warehouse warehouseRec = getWarehouse(shipmentDTO.getReceiveBranchId());
        Shipment shipment = new Shipment(null,warehouse, shipmentDTO.getClientSendingName(), shipmentDTO.getClientReceiveName(),
                shipmentDTO.getSendDate().getTime(),shipmentDTO.getAddress(),warehouseRec,null,null,null);
        shipment=shipmentRepository.save(shipment);
        System.out.println(shipmentDTO.getSendDate().getTime());
        Set<Package> packs = new HashSet<>();

        for (PackageDTO pa: shipmentDTO.getPackages()) {
            Package pack = new Package(null, pa.getWeightLbs(),pa.getDescription(), shipment,pa.getSubTotal());
            packs.add(packageRepository.save(pack));
        }
        shipment.setShipmentPayment(economicService.createShipmentPayment(shipment,shipmentDTO.getIsPaid(),shipmentDTO.getTotal(),
                (shipmentDTO.getIsPaid())?shipmentDTO.getPayDate():null));
        routeService.findRoute(shipment,shipmentDTO.getTotal().doubleValue());
        shipment.setPackages(packs);

        return shipment;
    }

    public ShipmentPayment payShipment(Integer id){
        return economicService.payShipment(id);

    }


    public List<ShipmentHistory> registerEntrance(WarehouseMovementDTO warehouseMovementDTO){
        ShipmentHistory shipmentHistory;

        Warehouse warehouse= getWarehouse(warehouseMovementDTO.getWarehouseId());
        Trip trip= getTrip(warehouseMovementDTO.getTripId());
        List<ShipmentHistory> histories = new ArrayList<>();
        for (Integer shipmentId: warehouseMovementDTO.getShipmentId()) {
            Shipment shipment= getShipment(shipmentId);
            if(warehouse.getId()==shipment.getReceiveWarehouse().getId()){
                shipmentHistory=new ShipmentHistory(null,shipment,HistoryState.READY,warehouseMovementDTO.getDate(),warehouse,null);
            }else{
                Trip nextTrip = getNextTrip(shipment,trip);
                shipmentHistory=new ShipmentHistory(null,shipment,HistoryState.ENTRANCE,warehouseMovementDTO.getDate(),warehouse,nextTrip);
            }
            histories.add(shipmentHistory);
        }

        histories=shipmentHistoryRepository.saveAll(histories);
        return histories;
    }

    public List<ShipmentHistory> registerDeparture(WarehouseMovementDTO warehouseMovementDTO) {
        ShipmentHistory shipmentHistory;
        Warehouse warehouse= getWarehouse(warehouseMovementDTO.getWarehouseId());
        Trip trip= getTrip(warehouseMovementDTO.getTripId());
        List<ShipmentHistory> histories = new ArrayList<>();
        for (Integer shipmentId: warehouseMovementDTO.getShipmentId()) {
            Shipment shipment= getShipment(shipmentId);
            Optional<ShipmentTrip> shipmentTripOptional = shipmentTripRepository.findByShipmentAndTrip(shipment,trip);
            if(shipmentTripOptional.isPresent()){
                shipmentHistory=new ShipmentHistory(null,shipment,HistoryState.IN_ROUTE,warehouseMovementDTO.getDate(),warehouse,trip);
            }else{
                throw new APIException(HttpStatus.CONFLICT,"Package (ID:"+shipmentId +") should not board Vehicle");
            }
            histories.add(shipmentHistory);
        }

        histories=shipmentHistoryRepository.saveAll(histories);
        return histories;
    }

    public Trip getNextTrip(Shipment shipment, Trip trip){
        Integer number=shipmentTripRepository.getNumber(shipment,trip);
        return shipmentTripRepository.getTrip(shipment,number+1);
    }

    public ShipmentHistory receive(ReceiveDTO receiveDTO){
        Shipment shipment= getShipment(receiveDTO.getShipmentId());
        Warehouse warehouse= getWarehouse(receiveDTO.getWarehouseId());
        ShipmentHistory shipmentHistory= new ShipmentHistory(null, shipment, HistoryState.RECEIVED,receiveDTO.getDate(), warehouse,null);
        return shipmentHistoryRepository.save(shipmentHistory);
    }

    public Shipment getShipment(Integer id){
        Optional<Shipment> optionalShipment= shipmentRepository.findById(id);
        if(optionalShipment.isEmpty()){
            throw new ResourceNotFoundException("Shipment","ID",id);
        }
        return optionalShipment.get();
    }

    public Warehouse getWarehouse(Integer id) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (optionalWarehouse.isEmpty()) {
            throw new ResourceNotFoundException("Warehouse", "ID", id);
        }
        return optionalWarehouse.get();
    }
    public Trip getTrip(Integer id) {
        return routeService.getTrip(id);
    }

    public String getQR(Integer id) throws IOException, WriterException {
        Optional<Shipment> shipment = shipmentRepository.findById(id);
        if (shipment.isEmpty()) return "";
        String data = urlLocalize + id.toString();
        Map<EncodeHintType, Object> hintMap = new HashMap<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height, hintMap);

        BufferedImage qrImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                qrImage.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }

        ByteArrayOutputStream qr = new ByteArrayOutputStream();
        ImageIO.write(qrImage, "png", qr);

        return Base64.encodeBase64String(qr.toByteArray());
    }

    public ShipmentHistoryDTO locatePackage(Integer id) {
        Optional<ShipmentHistoryDTO> optionalShipmentHistoryDTO = shipmentHistoryRepository.findLatestShipmentPaymentDetails(id);
        if (optionalShipmentHistoryDTO.isEmpty()) {
            throw new ResourceNotFoundException("Shipment", "ID", id);
        }
        Shipment shipment = shipmentRepository.findById(id).get();
        optionalShipmentHistoryDTO.get().setDate(shipment.getExpectedDate());
        return optionalShipmentHistoryDTO.get();
    }

    public List<GuideHistoryDTO> getGuideHistory(Integer destination) {
        List<Object[]> results = shipmentHistoryRepository.findLatestShipmentHistoryByReceiveWarehouseId(destination);

        return results.stream()
                .map(GuideHistoryDTO::new)
                .filter(dto -> dto.getState() != HistoryState.RECEIVED)
                .collect(Collectors.toList());
    }

    public Integer createTrips(TripCreationDTO tripCreationDTO){
        return routeService.createTrips(tripCreationDTO);
    }
}
