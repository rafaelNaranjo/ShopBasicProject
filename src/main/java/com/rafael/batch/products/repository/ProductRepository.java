package com.rafael.batch.products.repository;

import com.rafael.batch.products.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByNameContainingAndPriceBetweenAndBrand_Name(String name, Double priceBegin, Double priceEnd, String brandName);
}
