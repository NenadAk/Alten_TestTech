package com.alten.sdc.techtest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alten.sdc.techtest.ProductNotFoundException;
import com.alten.sdc.techtest.model.ProductModel;
import com.alten.sdc.techtest.repositories.ProductRepository;

@Service
public class ProductServices {

     @Autowired
    private ProductRepository productRepository;

    public List<ProductModel> getAll() {
        return productRepository.findAll();
    }

    public ProductModel getProduct(Integer id) {
        
        return productRepository.findById(id)
         .orElseThrow(() -> new ProductNotFoundException(id));
        
    }

    public ProductModel addProduct(ProductModel newProduct) {
        return productRepository.save(newProduct);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    public void updateProduct(ProductModel productPatched) {
        productRepository.save(productPatched);
    }




} 