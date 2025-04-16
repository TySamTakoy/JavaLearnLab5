package com.example.lab5.persistence.orders;

import com.example.lab5.persistence.orders.items.OrderItemEntity;
import com.example.lab5.persistence.users.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @Setter(AccessLevel.PROTECTED)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(length = 2048)
    private String shippingAddress;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemEntity> orderItems;

    public enum OrderStatus {
        PENDING,
        SHIPPED,
        DELIVERED,
        CANCELED
    }

    public enum PaymentMethod {
        CREDIT_CARD,
        YOOMONEY,
        CASH_ON_DELIVERY
    }
}

