package com.example.TechnicalAssignment.controller;

import com.example.TechnicalAssignment.model.InventoryResponse;
import com.example.TechnicalAssignment.model.Order;
import com.example.TechnicalAssignment.model.OrderResponse;
import com.example.TechnicalAssignment.model.pricingResponse;
import com.example.TechnicalAssignment.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@RestController
public class demoController {
    @Autowired
    OrderService service;

    //external api code
    pricingResponse pricing;
    InventoryResponse inventory;


    public demoController(OrderService service){
        this.service=service;

    }

    //POST Requests
    @PostMapping("/orders")
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody Order order){
        System.out.println("post starts  now");

        Order saved=service.createOrder(order);
        OrderResponse response= new OrderResponse(saved.getId(),saved.getStatus());
        System.out.println("post runs");
        return ResponseEntity.ok(response);

    }
    @Async
    @PostMapping("/orders/batch-upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
        if(file.isEmpty()){
            return ResponseEntity.badRequest().body("file empty");
        }
        service.uploadFile(file);
        return ResponseEntity.ok("file upload is successful");
    }







    //GET Requests
    //to check GET is running request all orders first
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrder(){
        return ResponseEntity.ok(service.getOrder());

    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Optional<Order>> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));
    }

}