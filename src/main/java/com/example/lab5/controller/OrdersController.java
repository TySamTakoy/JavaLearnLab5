package com.example.lab5.controller;

import com.example.lab5.dto.OrderDTO;
import com.example.lab5.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final ShopService shopService;

    @GetMapping
    public List<OrderDTO> getOrder(Authentication authentication) {
        return shopService.getOrder(authentication);
    }

    @PutMapping
    public void createOrder(@RequestBody OrderDTO orderDTO, Authentication authentication) {
        shopService.createOrder(orderDTO, authentication);
    }
}
