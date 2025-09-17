package com.example.TechnicalAssignment.model;

import lombok.Getter;
import lombok.Setter;

public class pricingResponse {




    @Getter
    @Setter
    private String sku;
    @Getter
    @Setter
    private double unitPrice;
    @Getter
    @Setter
    private double totalPrice;

    /*public pricingResponse(String sku, double unitPrice, double v) {
    }*/
}


