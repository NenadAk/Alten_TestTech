package com.alten.sdc.techtest.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alten.sdc.techtest.model.ProductModel;
import com.alten.sdc.techtest.repositories.ProductRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    @GetMapping("")
    public List<ProductModel> findAll() {
        return productRepository.findAll();
    }
    


}