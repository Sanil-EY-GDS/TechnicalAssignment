package com.example.TechnicalAssignment.services;

import com.example.TechnicalAssignment.model.InventoryResponse;
import com.example.TechnicalAssignment.model.Order;
import com.example.TechnicalAssignment.model.pricingResponse;
import com.example.TechnicalAssignment.orderDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;


@Service
@Component
public class OrderService {
    @Autowired
    orderDB db;
    @Autowired
    external ext;


    public List<Order> getOrder() {
        return db.findAll();
    }
    public Optional<Order> getById(Long id) {
        return db.findById(id);
    }
    public Order createOrder(Order order ){
        pricingResponse pricing=ext.getPricing(order.getSku(), order.getQty());
        InventoryResponse inventory=ext.reserveInventory(order.getSku(),order.getQty());

        if (pricing == null) {
            order.setStatus("FAILED_PRICING");
        }
        else if (inventory == null || !inventory.isReserved()) {
            order.setStatus("FAILED_INVENTORY");
        }
        else {
            //pricing api call after inventory check only
            Double totalPrice= pricing.getUnitPrice()*order.getQty();
            order.setTotalPrice(totalPrice);
            order.setStatus("CONFIRMED");
        }

        //pre external code-
        return db.save(order);
    }

    public void show() {
    }
    public void uploadFile(MultipartFile file){
        try(BufferedReader reader=new BufferedReader(new InputStreamReader(file.getInputStream()))){
             reader.lines().skip(1).forEach(line ->{

                 try{
                     String[] data=line.split(",");
                     String custid=data[0].trim();
                     String sku=data[1].trim();
                     int qty=Integer.parseInt((data[2].trim()));
                     //this runs for each time and order is detected in csv
                        System.out.println("batch order parsed");
                        Order order=new Order();
                        order.setCustID(custid);
                        order.setSku(sku);
                        order.setQty(qty);
                        order.setStatus("CONFIRMED");
                        System.out.println("batch order saved");
                     db.save(order);
                 } catch (Exception e) {
                     throw new RuntimeException(e);
                 }
             });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
