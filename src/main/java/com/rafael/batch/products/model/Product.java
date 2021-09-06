package com.rafael.batch.products.model;

import com.rafael.batch.brand.modal.Brand;
import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Double price;
    private Integer qty;
    @Column(name = "percent_descount")
    private Double percentDescount;
    private String state;
    @OneToOne
    @JoinColumn(name = "brand_id",referencedColumnName = "id")
    private Brand brand;
}
