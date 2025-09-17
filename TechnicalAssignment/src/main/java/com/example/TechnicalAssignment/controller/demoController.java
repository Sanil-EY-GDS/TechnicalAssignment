package com.example.TechnicalAssignment.controller;

import com.example.TechnicalAssignment.model.InventoryResponse;
import com.example.TechnicalAssignment.model.Order;
import com.example.TechnicalAssignment.model.OrderResponse;
import com.example.TechnicalAssignment.model.pricingResponse;
import com.example.TechnicalAssignment.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class demoController {
    @Autowired
    OrderService service;
    //ext api code
    pricingResponse pricing;
    InventoryResponse inventory;


    public demoController(OrderService service){
        this.service=service;

    }

    //POST Requests
    @PostMapping("/orders")
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody Order order){
        System.out.println("post starts");

        /*// mock pricing API
        pricingResponse pricing = new pricingResponse();
        pricing.setSku(order.getSku());
        pricing.setUnitPrice(20.0);
        pricing.setTotalPrice(order.getQty() * 20.0);

        //  mock inventory API
        InventoryResponse inventory = new InventoryResponse();
        inventory.setSku(order.getSku());
        inventory.setReservedQty(order.getQty());
        inventory.setReserved(true);
        System.out.println("inventory called");*/
        //ext ends


        Order saved=service.createOrder(order);
        OrderResponse response= new OrderResponse(saved.getId(),saved.getStatus());
        System.out.println("post runs");
        return ResponseEntity.ok(response);

    }
    @PostMapping("/orders/batch-upload")
    /*public String uploadFile((@RequestBody("file") MultipartFile file){

    }*/







    //GET Requests
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrder(){
//      System.out.println("get runs");
        return ResponseEntity.ok(service.getOrder());

    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Optional<Order>> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));
    }

}