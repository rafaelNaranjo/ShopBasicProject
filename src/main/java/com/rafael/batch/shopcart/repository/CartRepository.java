package com.rafael.batch.shopcart.repository;

import com.rafael.batch.shopcart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
