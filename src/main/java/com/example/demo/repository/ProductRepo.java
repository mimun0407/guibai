package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    List<Product>findAllByPriceBetween(int price1,int price2);
    List<Product>findAllByOrderByAmount();
    @Modifying
    @Query(value ="SELECT * FROM product order by price desc  limit 3" ,nativeQuery = true)
    List<Product>findByPrice();
    List<Product>findAllByCategoryName_NameCategory(String name);
}
