package com.mayaexpress.service;

import com.mayaexpress.entity.Price;
import com.mayaexpress.entity.Shipment;
import com.mayaexpress.entity.ShipmentPayment;
import com.mayaexpress.exception.ResourceNotFoundException;
import com.mayaexpress.repository.PriceRepository;
import com.mayaexpress.repository.ShipmentPaymentRespository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EconomicService {

    private final PriceRepository priceRepository;

    private final ShipmentPaymentRespository shipmentPaymentRespository;

    public EconomicService(PriceRepository priceRepository, ShipmentPaymentRespository shipmentPaymentRespository) {
        this.priceRepository = priceRepository;
        this.shipmentPaymentRespository = shipmentPaymentRespository;
    }

    public List<Price> getPrices(){
        return priceRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Price getPrice(Integer id){
        Optional<Price> price = priceRepository.findById(id);
        if (price.isEmpty()) throw new ResourceNotFoundException("Price","id",id);
        return price.get();
    }

    public Price getPriceByDepartments(Integer idOrigin, Integer idDestination){
        Optional<Price> price = priceRepository.findByOrigenAndDestination(idOrigin,idDestination);
        if (price.isEmpty()) throw new ResourceNotFoundException("Price","idOrigin",idOrigin);
        return price.get();
    }

    public Price updatePrice(Price price, Integer id){
        Optional<Price> opOldPrice = priceRepository.findById(id);
        if (opOldPrice.isEmpty()) throw new ResourceNotFoundException("Price","id",id);
        Price oldPrice = opOldPrice.get();
        if(price.getCostPerLb()!=null)oldPrice.setCostPerLb(price.getCostPerLb());
        if(price.getSendingCost()!=null)oldPrice.setSendingCost(price.getSendingCost());
        return priceRepository.save(oldPrice);

    }

    public ShipmentPayment createShipmentPayment(Shipment shipment, Boolean isPaid, BigDecimal total, Date date){
        ShipmentPayment shipmentPayment = new ShipmentPayment(null, shipment,isPaid, total, date);
        return shipmentPayment;
        //return shipmentPaymentRespository.save(shipmentPayment);
    }

    public ShipmentPayment payShipment(Integer id){
        Optional<ShipmentPayment> optionalShipmentPayment = shipmentPaymentRespository.findById(id);
        if (optionalShipmentPayment.isEmpty()) throw new ResourceNotFoundException("Shipment Payment","id",id);
        ShipmentPayment payment = optionalShipmentPayment.get();
        payment.setIsPaid(true);
        payment.setDate(new Date());
        return shipmentPaymentRespository.save(payment);
    }
}
