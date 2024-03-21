package com.example.demo.service;

import com.example.demo.model.Product;

import java.util.Optional;

public interface IService {
    Iterable<Product> findAll();

    Optional<Product> findById(int id);

    Product save(Product e);

    void remove(int id);
}
