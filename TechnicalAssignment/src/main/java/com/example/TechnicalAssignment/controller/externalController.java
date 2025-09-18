package com.example.TechnicalAssignment.controller;

import com.example.TechnicalAssignment.model.InventoryResponse;
import com.example.TechnicalAssignment.model.pricingResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/external")
public class externalController {

    //pricing and inventory api
    //integrated with the demoController, running on the same port
    @GetMapping("/pricing/{sku}")
    public ResponseEntity<pricingResponse> getPricing(@PathVariable String sku,
                                                      @RequestParam(defaultValue = "1") int qty) {
        pricingResponse response = new pricingResponse();
        //response.setSku(sku);
        response.setUnitPrice(100.0);
        response.setTotalPrice(qty * 1001);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/inventory/reserve")
    public ResponseEntity<InventoryResponse> reserveInventory(@RequestParam String sku,
                                                              @RequestParam int qty) {
        InventoryResponse response = new InventoryResponse();
        response.setSku(sku);
        response.setReservedQty(qty);
        response.setReserved(true);
        return ResponseEntity.ok(response);
    }
}