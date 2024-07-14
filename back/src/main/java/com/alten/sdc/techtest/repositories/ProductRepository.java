package com.alten.sdc.techtest.repositories;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.alten.sdc.techtest.model.ProductModel;

@Repository
public interface ProductRepository extends ListCrudRepository<ProductModel, Integer> {
}