package com.example.lab5.controller;

import com.example.lab5.dto.CartDTO;
import com.example.lab5.service.ShopService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
@RequestMapping("/carts")
public class CartsController {

    private final ShopService shopService;

    public CartsController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping
    public CartDTO getCart(Authentication authentication) {
        return shopService.getUserCart(authentication);
    }

    @PostMapping
    public void addToCart(@RequestParam Long productId, @RequestParam Integer quantity, Authentication authentication) {
        shopService.addToCart(productId, quantity, authentication);
    }

    @DeleteMapping("/{productId}")
    public void removeFromCart(@PathVariable Long productId, @RequestParam Integer quantity, Authentication authentication) {
        shopService.removeFromCart(productId, quantity,  authentication);
    }

    @DeleteMapping
    public void clearCart(Authentication authentication) {
        shopService.clearCart(authentication);
    }
}
