package com.alten.sdc.techtest;

public class ProductNotFoundException extends RuntimeException{
    
    public ProductNotFoundException(Integer id) {
        super("Could not find product " + id);
    }
}