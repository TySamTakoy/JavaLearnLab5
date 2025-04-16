package com.example.lab5.dto;

import java.util.List;

public class CartDTO {
    public Long userId;
    public List<CartItemDTO> items;

    public static class CartItemDTO {
        public Long productId;
        public Integer quantity;
    }
}
