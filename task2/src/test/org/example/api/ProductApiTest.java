package com.example.productapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductApiValidationTest {

    private final String API_URL = "https://fakestoreapi.com/products";

    @Test
    public void validateProductApi() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(API_URL, String.class);

        // ✅ Check status code
        assertEquals(200, response.getStatusCodeValue(), "Response status should be 200");

        // ✅ Deserialize JSON
        ObjectMapper mapper = new ObjectMapper();
        List<Product> products = mapper.readValue(response.getBody(), new TypeReference<List<Product>>() {});

        List<String> defectiveProducts = new ArrayList<>();

        for (Product product : products) {
            StringBuilder defect = new StringBuilder();

            if (product.getTitle() == null || product.getTitle().trim().isEmpty()) {
                defect.append("Missing or empty title. ");
            }

            if (product.getPrice() == null || product.getPrice() < 0) {
                defect.append("Negative or null price. ");
            }

            if (product.getRating() == null || product.getRating().getRate() == null || product.getRating().getRate() > 5) {
                defect.append("Invalid rating (must not exceed 5). ");
            }

            if (!defect.toString().isEmpty()) {
                defectiveProducts.add("Product: " + product.getTitle() + " -> " + defect);
            }
        }

        // ✅ Log defective products
        if (!defectiveProducts.isEmpty()) {
            System.out.println("Defective products found:");
            defectiveProducts.forEach(System.out::println);
        }

        // ✅ Final assertion (optional)
        assertTrue(defectiveProducts.isEmpty(), "Some products contain data defects");
    }
}