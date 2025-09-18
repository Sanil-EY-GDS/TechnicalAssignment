package com.example.TechnicalAssignment.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity


@Table(name="orders")
public class Order {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    //making sure the request json has all the keys in POST/orders
    @NotBlank(message="customer id is required")
    @Column(nullable = false)
    private String custID;

    @Setter
    @Getter
    @NotNull(message="qty  is required")
    @Column(nullable = false)
    private Integer qty;

    @Setter
    @Getter
    @NotBlank(message="sku is required")
    private String sku;


    @Setter
    @Getter
    private String status;

    @Getter
    @Setter
    private double totalPrice;


    public Order(){

    }
    public Order(String custID,int qty, String sku, String status){
        this.custID=custID;
        this.qty=qty;
        this.sku=sku;
        this.status=status;
        this.totalPrice=totalPrice;
    }

}
