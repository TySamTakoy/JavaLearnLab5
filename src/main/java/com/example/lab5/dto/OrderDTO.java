package com.example.lab5.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

public class OrderDTO {

    public Long id;
    public Long userId;
    public List<OrderItemDTO> products;
    public BigDecimal totalPrice;
    public String status;
    public String shippingAddress;
    public String paymentMethod;

    public static class OrderItemDTO {
        public Long productId;
        public Integer quantity;
        public BigDecimal unitPrice;
    }

    public static class OrderRequestDTO {
        public String shippingAddress;
        public String paymentMethod;
    }

}
