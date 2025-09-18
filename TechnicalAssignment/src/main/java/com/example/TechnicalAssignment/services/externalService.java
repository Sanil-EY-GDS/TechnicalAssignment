package com.example.TechnicalAssignment.services;

import com.example.TechnicalAssignment.model.InventoryResponse;
import com.example.TechnicalAssignment.model.pricingResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class externalService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String pricingBaseUrl = "http://localhost:8080/external/pricing";
    private final String inventoryBaseUrl = "http://localhost:8080/external/inventory";

    public pricingResponse getPricing(String sku, int qty) {
        System.out.println("pricing new api called");
        pricingResponse response = restTemplate.getForObject(
            pricingBaseUrl +"/" + sku , pricingResponse.class
        );
        System.out.println(response.getTotalPrice());
        return response;
    }

    public InventoryResponse reserveInventory(String sku, int qty) {
        InventoryResponse response = restTemplate.postForObject(
                inventoryBaseUrl + "/reserve?sku=" + sku + "&qty=" + qty,
                null,
                InventoryResponse.class
        );
        System.out.println("inventory new api runs");

        return response;
    }
}