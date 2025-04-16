package com.example.lab5.persistence.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT p FROM ProductEntity p WHERE p.name = :name AND p.price <= :price")
    List<ProductEntity> findAllByNameAndPriceLessThanEqual(String name, BigDecimal price);

    List<ProductEntity> findAllByName(String name);
}
