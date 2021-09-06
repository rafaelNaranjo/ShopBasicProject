package com.rafael.batch.products;

import com.rafael.batch.products.model.Product;
import com.rafael.batch.products.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/products")
public class ProductosController {

    @Autowired
    private ProductServices productServices;

    @GetMapping("/all")
    public ResponseEntity<Page<Product>> productsPageable(@PathParam("page") Integer page, @PathParam("limit") Integer limit, @PathParam("name") String name, @PathParam("price") Double price, @PathParam("priceEnd") Double priceEnd, @Param("brand") String brand){
        Page<Product> products = productServices.getProducts(name.isEmpty()?null:name.toUpperCase(),price,priceEnd,brand.isEmpty()?null:brand.toUpperCase(), PageRequest.of(page,limit));
        return ResponseEntity.ok().body(products);
    }

    @PostMapping("/{id}/validate/qty/{qty}")
    public ResponseEntity<Boolean> validationQtyProduct(@PathVariable Long id, @PathVariable Integer qty){
        boolean validation = productServices.validateQtyProduct(id,qty);
        return ResponseEntity.ok().body(validation);
    }
}
