package com.rafael.batch.jobs.jobproducts;

import com.rafael.batch.brand.modal.Brand;
import com.rafael.batch.brand.repository.BrandRepository;
import com.rafael.batch.products.model.Product;
import com.rafael.batch.products.repository.ProductRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ProductWriter implements ItemWriter<Product> {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BrandRepository brandRepository;


    @Override
    public void write(List<? extends Product> items) throws Exception {
        List<Brand> brands = items.stream().map(i->(Brand)i.getBrand()).filter(Objects::nonNull).collect(Collectors.toList());
        brandRepository.saveAllAndFlush(brands);
        List<Product> products = items.stream().map(i->(Product)i).filter(product -> product.getBrand()!=(null)).collect(Collectors.toList());
        productRepository.saveAll(products);
    }
}
