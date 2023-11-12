package com.mayaexpress.service;

import com.mayaexpress.dto.request.*;
import com.mayaexpress.entity.*;
import com.mayaexpress.entity.Package;
import com.mayaexpress.exception.APIException;
import com.mayaexpress.exception.ResourceNotFoundException;
import com.mayaexpress.repository.*;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShipmentService {
    private final DestinationRepository destinationRepository;


    private final ShipmentRepository shipmentRepository;

    private final PackageRepository packageRepository;

    private final EconomicService economicService;

    private final WarehouseRepository warehouseRepository;

    private final ShipmentHistoryRepository shipmentHistoryRepository;

    private final ShipmentTripRepository shipmentTripRepository;

    private final TripRepository tripRepository;


    public ShipmentService(DestinationRepository destinationRepository, EconomicService economicService,
                            WarehouseRepository warehouseRepository,
                           ShipmentRepository shipmentRepository, PackageRepository packageRepository,
                           ShipmentHistoryRepository shipmentHistoryRepository, ShipmentTripRepository shipmentTripRepository,
                           TripRepository tripRepository) {
        this.destinationRepository = destinationRepository;
        this.warehouseRepository=warehouseRepository;
        this.shipmentRepository=shipmentRepository;
        this.packageRepository=packageRepository;
        this.economicService=economicService;
        this.shipmentHistoryRepository=shipmentHistoryRepository;
        this.shipmentTripRepository= shipmentTripRepository;
        this.tripRepository= tripRepository;
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
                shipmentDTO.getSendDate(),shipmentDTO.getAddress(),warehouseRec,null,null,null);
        shipment=shipmentRepository.save(shipment);
        Set<Package> packs = new HashSet<>();
        for (PackageDTO pa: shipmentDTO.getPackages()) {
            Package pack = new Package(null, pa.getWeightLbs(),pa.getDescription(), shipment,pa.getSubTotal());
            packs.add(packageRepository.save(pack));
        }
        shipment.setShipmentPayment(economicService.createShipmentPayment(shipment,shipmentDTO.getIsPaid(),shipmentDTO.getTotal(),
                (shipmentDTO.getIsPaid())?shipmentDTO.getPayDate():null));

        shipment.setPackages(packs);
        return shipment;
    }

    public ShipmentPayment payShipment(Integer id){
        return economicService.payShipment(id);

    }

    public ShipmentHistory registerEntrance(WarehouseEntranceDTO warehouseEntranceDTO){
        ShipmentHistory shipmentHistory;
        Shipment shipment= getShipment(warehouseEntranceDTO.getShipmentId());
        Warehouse warehouse= getWarehouse(warehouseEntranceDTO.getWarehouseId());
        Optional<Trip> optionalTrip= tripRepository.findById(warehouseEntranceDTO.getTripId());
        if(optionalTrip.isEmpty()){
            throw new ResourceNotFoundException("Trip","ID",warehouseEntranceDTO.getTripId());
        }

        if(true){
            shipmentHistory=new ShipmentHistory(null,shipment,HistoryState.READY,new Date(),warehouse,null);
        }else{
            Trip nextTrip = getNextTrip(shipment,optionalTrip.get());
            shipmentHistory=new ShipmentHistory(null,shipment,HistoryState.READY,new Date(),warehouse,nextTrip);
        }
        shipmentHistory=shipmentHistoryRepository.save(shipmentHistory);
        return shipmentHistory;
    }

    public Trip getNextTrip(Shipment shipment, Trip trip){
        Integer number=shipmentTripRepository.getNumber(shipment,trip);
        return shipmentTripRepository.getTrip(shipment,number+1);
    }

    public ShipmentHistory receive(ReceiveDTO receiveDTO){
        Shipment shipment= getShipment(receiveDTO.getShipmentId());
        Warehouse warehouse= getWarehouse(receiveDTO.getWarehouseId());
        ShipmentHistory shipmentHistory= new ShipmentHistory(null, shipment, HistoryState.RECEIVED,new Date(), warehouse,null);
        return shipmentHistoryRepository.save(shipmentHistory);
    }

    public Shipment getShipment(Integer id){
        Optional<Shipment> optionalShipment= shipmentRepository.findById(id);
        if(optionalShipment.isEmpty()){
            throw new ResourceNotFoundException("Shipment","ID",id);
        }
        return optionalShipment.get();
    }

    public Warehouse getWarehouse(Integer id){
        Optional<Warehouse> optionalWarehouse= warehouseRepository.findById(id);
        if(optionalWarehouse.isEmpty()){
            throw new ResourceNotFoundException("Warehouse","ID",id);
        }
        return optionalWarehouse.get();
    }
}
