package com.rafael.batch.products.services;

import com.rafael.batch.brand.modal.Brand;
import com.rafael.batch.products.model.Product;
import com.rafael.batch.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

@Service
public class ProductServices {

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> getProducts(String name, Double price, Double priceEnd, String brand,Pageable pageable){
        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withMatcher("name", contains().ignoreCase());
        List<Product> products = productRepository.findAll(
                Example.of(
                        Product.builder()
                                .name(name)
                                .brand(Brand.builder().name(brand).build())
                                .build(),matcher
                )
        );
        products = products.stream()
                .filter(filterPrice(price, priceEnd))
                .collect(Collectors.toList());
        int start = Long.valueOf(pageable.getOffset()).intValue();
        int end = start + pageable.getPageSize();
        if(end>products.size()){
            end = products.size();
        }
        return new PageImpl<Product>(products.subList(start,end), pageable, products.size());
    }

    public boolean validateQtyProduct(Long id, Integer qty){
        Product product = productRepository.getById(id);
        return product.getQty() >= qty ;
    }

    public Product updateQtyProduct(Long id, Integer qty){
        Product product = productRepository.findById(id).get();
        product.setQty(product.getQty()-qty);
        return productRepository.save(product);
    }

    private Predicate<Product> filterPrice(Double price, Double priceEnd){
        return p->p.getPrice()>=price && p.getPrice()<=priceEnd;
    }
}
