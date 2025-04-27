package com.example.lab5.controller;

import com.example.lab5.dto.ProductDTO;
import com.example.lab5.service.ShopService;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private final ShopService shopService;

    public ProductsController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping
    public List<ProductDTO> getProducts(Pageable pageable) {
        return shopService.getProducts(pageable);
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id) {
        return shopService.getProduct(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping
    public void addProduct(@RequestBody ProductDTO productDTO) {
        shopService.updateProduct(productDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        productDTO.id = id;
        shopService.updateProduct(productDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        shopService.deleteProduct(id);
    }
}
