package com.rafael.batch.jobs.jobproducts;

import com.rafael.batch.brand.modal.Brand;
import com.rafael.batch.jobs.models.ProductDTO;
import com.rafael.batch.products.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ProductProccessor implements ItemProcessor<ProductDTO, Product> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductProccessor.class);

    private List<Brand> brands;
    private List<Product> products;

    @BeforeStep
    private void beforeStep(StepExecution stepExecution){
        brands = new ArrayList<>();
        products = new ArrayList<>();
        brands = (List<Brand>) stepExecution.getJobExecution().getExecutionContext().get("brands");
        products = (List<Product>) stepExecution.getJobExecution().getExecutionContext().get("products");
    }

    @Override
    public Product process(ProductDTO item) throws Exception {
        if(item.isSomeFieldNull()){
            return null;
        }else{
            Brand brand = brands.stream().filter(brandByName(item.getMarca().toUpperCase())).findFirst().orElse(Brand.builder().name(item.getMarca().toUpperCase()).build());
            if(brand.getId()==null){
                brands.add(brand);
            }
            Product product = products.stream().filter(productByName(item.getNombre().toUpperCase())).findFirst().orElse(createProduct(item,brand));
            if(product.getId()!=null){
                return null;
            }else {
                return product;
            }
        }
    }

    private Predicate<Brand> brandByName(String name){
        return brand -> brand.getName().equals(name);
    }

    private Predicate<Product> productByName(String name){
        return product -> product.getName().equals(name);
    }

    private Product createProduct(ProductDTO item, Brand brand){
        return Product.builder()
                .name(item.getNombre().toUpperCase())
                .price(Double.parseDouble(item.getPrecio()))
                .qty(Integer.parseInt(item.getCantidad()))
                .percentDescount(Double.parseDouble(item.getDescuento()))
                .state(item.getEstado().toUpperCase())
                .brand(brand)
                .build();
    }
}
