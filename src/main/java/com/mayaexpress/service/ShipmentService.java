package com.mayaexpress.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.mayaexpress.dto.request.BranchDTO;
import com.mayaexpress.dto.request.PackageDTO;
import com.mayaexpress.dto.request.ShipmentDTO;
import com.mayaexpress.dto.request.VehicleDTO;
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
import java.math.BigDecimal;
import java.util.*;

@Service
public class ShipmentService {
    private final DestinationRepository destinationRepository;


    private final BranchRepository branchRepository;

    private final ShipmentRepository shipmentRepository;

    private final PackageRepository packageRepository;

    private final EconomicService economicService;

    @Value("${url-maya-express-qr}")
    private String urlLocalize;

    @Value("${qr.height}")
    private int height;

    @Value("${qr.width}")
    private int width;

    public ShipmentService(DestinationRepository destinationRepository, EconomicService economicService,
                           BranchRepository branchRepository,
                           ShipmentRepository shipmentRepository, PackageRepository packageRepository) {
        this.destinationRepository = destinationRepository;
        this.branchRepository=branchRepository;
        this.shipmentRepository=shipmentRepository;
        this.packageRepository=packageRepository;
        this.economicService=economicService;
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
        Optional<Branch> branchOptional=branchRepository.findById(shipmentDTO.getBranchId());
        if(branchOptional.isEmpty()) throw new ResourceNotFoundException("Branch","ID",shipmentDTO.getBranchId());
        Optional<Branch> branchRecOptional=branchRepository.findById(shipmentDTO.getReceiveBranchId());
        if(branchRecOptional.isEmpty()) throw new ResourceNotFoundException("Branch","ID",shipmentDTO.getReceiveBranchId());
        Shipment shipment = new Shipment(null,branchOptional.get(), shipmentDTO.getClientSendingName(), shipmentDTO.getClientReceiveName(), 
                shipmentDTO.getSendDate(),shipmentDTO.getAddress(),branchRecOptional.get(),null,null,null);
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

    public String getQR(Integer id) throws IOException, WriterException {
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
}
