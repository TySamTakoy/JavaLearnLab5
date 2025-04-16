package com.example.lab5.service;


import com.example.lab5.persistence.products.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TestService {

    private final ProductRepository productRepository;

    public TestService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void test () {
        productRepository.findAllByNameAndPriceLessThanEqual("baba", new BigDecimal("145.25"));
    }
}
