package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepo;
import com.example.demo.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class controller {
    @Autowired
    ProductRepo  productRepo;

    @Autowired
    IService iCustomerService;
    @GetMapping
    public ResponseEntity<Iterable<Product>> findAllCustomer() {
        List<Product> customers = (List<Product>) iCustomerService.findAll();
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findCustomerById(@PathVariable int id) {
        Optional<Product> customerOptional = iCustomerService.findById(id);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> saveCustomer(@RequestBody Product product) {
        return new ResponseEntity<>(iCustomerService.save(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateCustomer(@PathVariable int id, @RequestBody Product customer) {
        Optional<Product> customerOptional = iCustomerService.findById(id);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customer.setId(customerOptional.get().getId());
        return new ResponseEntity<>(iCustomerService.save(customer), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteCustomer(@PathVariable int id) {
        Optional<Product> customerOptional = iCustomerService.findById(id);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iCustomerService.remove(id);
        return new ResponseEntity<>(customerOptional.get(), HttpStatus.OK);
    }
    @GetMapping("/price")
    public ResponseEntity findByPrice(@RequestParam int price1,@RequestParam int price2) {
        return new ResponseEntity<>(productRepo.findAllByPriceBetween(price1,price2), HttpStatus.OK);
    }
    @GetMapping("/amount")
    public ResponseEntity findByAmount() {
        return new ResponseEntity<>(productRepo.findAllByOrderByAmount(), HttpStatus.CREATED);
    }
    @GetMapping("/high")
    public ResponseEntity findByHigh() {
        return new ResponseEntity<>(productRepo.findByPrice(), HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity findByAmount(@RequestParam String name) {
        return new ResponseEntity<>(productRepo.findAllByCategoryName_NameCategory(name), HttpStatus.CREATED);
    }
}
