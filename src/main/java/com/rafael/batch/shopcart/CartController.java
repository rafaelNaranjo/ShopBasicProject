package com.rafael.batch.shopcart;

import com.rafael.batch.shopcart.model.Cart;
import com.rafael.batch.shopcart.services.CartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartServices cartServices;

    @PostMapping("/new")
    public ResponseEntity<Cart> createNewCart(@RequestBody Cart cart){
        Cart cartDb = cartServices.createUpdateCart(cart);
        return ResponseEntity.ok().body(cartDb);
    }

    @PutMapping("/update")
    public ResponseEntity<Cart> updateCart(@RequestBody Cart cart){
        Cart cartDb = cartServices.createUpdateCart(cart);
        return ResponseEntity.ok().body(cartDb);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> DeleteCart(@RequestBody Cart cart){
        boolean delete = cartServices.deleteCart(cart);
        return ResponseEntity.ok().body(delete);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> createNewCart(@PathVariable Long id){
        Cart cartDb = cartServices.getCart(id);
        return ResponseEntity.ok().body(cartDb);
    }
    @PostMapping("/{id}/complete")
    public ResponseEntity<Cart> completeCart(@PathVariable Long id){
        Cart cartDb = cartServices.finishCartShop(id);
        return ResponseEntity.ok().body(cartDb);
    }

}
