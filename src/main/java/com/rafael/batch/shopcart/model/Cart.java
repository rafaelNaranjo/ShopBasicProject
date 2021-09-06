package com.rafael.batch.shopcart.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ElementCollection
    @CollectionTable(name = "item", joinColumns = @JoinColumn(name = "cart_id"))
    private List<Item> items;
    private Double total;
    private String state;
}
