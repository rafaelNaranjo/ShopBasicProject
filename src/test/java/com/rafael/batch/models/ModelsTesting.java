package com.rafael.batch.models;

import com.rafael.batch.brand.modal.Brand;
import com.rafael.batch.jobs.models.ProductDTO;
import com.rafael.batch.products.model.Product;
import org.junit.jupiter.api.Test;
import pl.pojo.tester.api.assertion.Method;

import static pl.pojo.tester.api.assertion.Assertions.*;

public class ModelsTesting {

    @Test
    public void TestingDtos(){
        final Class[] classesUnderTest = {
                ProductDTO.class,
                Product.class,
                Brand.class
        };

        assertPojoMethodsForAll(classesUnderTest)
                .quickly()
                .testing(Method.CONSTRUCTOR)
                .testing(Method.GETTER)
                .testing(Method.SETTER)
                .areWellImplemented();
    }
}
