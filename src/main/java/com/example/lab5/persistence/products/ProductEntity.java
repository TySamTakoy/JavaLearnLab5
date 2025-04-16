package com.example.lab5.persistence.products;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.net.URL;

@Getter
@Setter
@ToString
@Entity
@Table(name="products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @Setter(AccessLevel.PROTECTED)
    private Long id;

    @Column(name = "name", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String name;

    @Column(name = "price", nullable = false)
    @JdbcTypeCode(SqlTypes.NUMERIC)
    private BigDecimal price;

    @Column(name = "image", nullable = false, length = 512)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private URL image;

    @Column(name = "description", length = 2048)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String description;

}
