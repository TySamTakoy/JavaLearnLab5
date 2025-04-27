package com.example.lab5.repository;

import com.example.lab5.entity.OrderEntity;
import com.example.lab5.entity.OrderItemEntity;
import com.example.lab5.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findAllByUser(UserEntity user);

}
