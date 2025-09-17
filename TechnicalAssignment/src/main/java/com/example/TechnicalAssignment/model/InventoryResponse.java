package com.example.TechnicalAssignment.model;

import lombok.Getter;
import lombok.Setter;

public class InventoryResponse {


    @Getter
    @Setter
    private String sku;
    @Setter
    @Getter
    private int reservedQty;
    @Setter@Getter
    private boolean reserved;

    /*public InventoryResponse(String sku,int reservedQty, boolean reserved){
            this.sku=sku;
            this.reservedQty=reservedQty;
            this.reserved=reserved;
        }
*/


}
