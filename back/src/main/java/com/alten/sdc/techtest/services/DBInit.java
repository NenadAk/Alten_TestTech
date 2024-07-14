package com.alten.sdc.techtest.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.alten.sdc.techtest.model.ProductModel;
import com.alten.sdc.techtest.repositories.ProductRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import aj.org.objectweb.asm.TypeReference;

@Component
public class DBInit implements CommandLineRunner {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ProductRepository productRepository;


    @Override
    public void run(String... args) throws Exception {
       
        JsonNode json;
        List<ProductModel> products = new ArrayList<>();

        try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/products.json")){
            json = objectMapper.readValue(inputStream, JsonNode.class );
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON data", e);
        }

        JsonNode datas = getData(json);
        for (JsonNode data : datas){
            products.add(createProductFromData(data));
        }

        productRepository.saveAll(products);
    }

    private ProductModel createProductFromData(JsonNode data) {
        Integer id = data.get("id").asInt();
        String code = data.get("code").asText();
        String name = data.get("name").asText();
        String description = data.get("description").asText();
        String image = data.get("image").asText();
        Integer price = data.get("price").asInt();
        String category = data.get("category").asText();
        Integer quantity = data.get("quantity").asInt();
        String inventoryStatus = data.get("inventoryStatus").asText();
        Integer rating = data.get("rating").asInt();

        return new ProductModel(id, code, name, description, image, price, category, quantity, inventoryStatus, rating);
    }

    private JsonNode getData(JsonNode json){
        return Optional.ofNullable(json)
                .map(j -> j.get("data"))
                .orElseThrow(() -> new IllegalArgumentException("Invalid JSON Object"));
    }
    
}