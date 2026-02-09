package com.jpahybernatepractice.jpa_hybernate_practice.Entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(
        name = "product_table",
        uniqueConstraints = {
                @UniqueConstraint(name = "sku_unique", columnNames = {"sku"}),
                @UniqueConstraint(name="price_quantity_unique", columnNames = {"price", "quantity"})
        },
        indexes = {
                @Index(name = "sku_index", columnList = "sku"),
                @Index(name = "title_index", columnList = "title")
        }
)
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, length = 20)
    String sku;
    String title;
    BigDecimal price;
    Integer quantity;
    @Column(name = "creation_time")
            @CreationTimestamp
    LocalDateTime createdAt;
    @Column(name = "last_updation")
            @UpdateTimestamp
    LocalDateTime updatedAt;
}
