package com.example.lab5.persistence.users;

import com.example.lab5.persistence.products.ProductEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name="users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @Setter(AccessLevel.PROTECTED)
    private Long id;


    @Column(name = "full_name", length = 1024)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String fullName;

    @Column(name = "password_hash", length = 1024)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String passwordHash;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "carts",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<ProductEntity> productEntities = new ArrayList<>();

}
