package com.rafael.batch.shopcart.model;

import com.rafael.batch.products.model.Product;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    private int qty;
}
