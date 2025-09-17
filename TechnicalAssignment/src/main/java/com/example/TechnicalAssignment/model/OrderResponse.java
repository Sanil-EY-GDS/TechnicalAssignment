package com.example.TechnicalAssignment.model;

import lombok.Getter;

public class OrderResponse {
    @Getter
    private Long id;
    @Getter
    private String status;

    public OrderResponse(Long id, String status){
        this.id=id;
        this.status=status;
    }
}
