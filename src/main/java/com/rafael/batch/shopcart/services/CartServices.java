package com.rafael.batch.shopcart.services;

import com.rafael.batch.products.services.ProductServices;
import com.rafael.batch.shopcart.model.Cart;
import com.rafael.batch.shopcart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CartServices {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductServices productServices;

    public Cart createUpdateCart(Cart cart){
        return cartRepository.save(cart);
    }
    public boolean deleteCart(Cart cart){
        try{
            cartRepository.delete(cart);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public Cart getCart(Long id){
        return cartRepository.findById(id).get();
    }

    public Cart finishCartShop(Long cartId){
        Cart cart = cartRepository.findById(cartId).get();
        cart.getItems().stream().forEach(p->productServices.updateQtyProduct(p.getProduct().getId(), p.getQty()));
        cart.setState("COMPLETE");
        cartRepository.save(cart);
        return  cart;
    }

}
