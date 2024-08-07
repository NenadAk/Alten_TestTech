package com.alten.sdc.techtest.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alten.sdc.techtest.ProductNotFoundException;
import com.alten.sdc.techtest.model.ProductModel;
import com.alten.sdc.techtest.services.ProductServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    @Autowired
    private ProductServices productServices;
    @Autowired
    private ObjectMapper objectMapper;


    @GetMapping("")
    public List<ProductModel> findAll() {
        return productServices.getAll();
    }

    @GetMapping("/{id}")
    public ProductModel findOneProduct(@PathVariable Integer id){
        return productServices.getProduct(id);
    }

    @PostMapping("")
    public ProductModel addProduct(@RequestBody ProductModel newProduct) {
        
        return productServices.addProduct(newProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id){
        productServices.deleteProduct(id);
    }

    @PatchMapping(path="/id", consumes = "application/json-patch+json")
    public ResponseEntity<ProductModel> updateDetailsOfProduct(@PathVariable Integer id, @RequestBody JsonPatch patch) {
        try {
            ProductModel product = productServices.getProduct(id);
            ProductModel productPatched = applyPatchToProduct(patch, product);
            productServices.updateProduct(productPatched);
            return ResponseEntity.ok(productPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    private ProductModel applyPatchToProduct(JsonPatch patch, ProductModel targetProduct) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetProduct, JsonNode.class));
        return objectMapper.treeToValue(patched, ProductModel.class);
    }
    
}