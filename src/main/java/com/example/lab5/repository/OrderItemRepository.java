package com.example.lab5.repository;

import com.example.lab5.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
    List<OrderItemEntity> findByOrder_Id(Long id);

    void deleteByOrder_Id(Long id);

    void deleteByProduct_Id(Long id);
}
