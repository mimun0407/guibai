package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements IService{
    @Autowired
    private ProductRepo iCustomerRepository;

    @Override
    public Iterable<Product> findAll() {
        return iCustomerRepository.findAll();
    }

    @Override
    public Optional<Product> findById(int id) {
        return iCustomerRepository.findById(id);
    }

    @Override
    public Product save(Product customer) {
        return iCustomerRepository.save(customer);
    }

    @Override
    public void remove(int id) {
        iCustomerRepository.deleteById(id);
    }
}
