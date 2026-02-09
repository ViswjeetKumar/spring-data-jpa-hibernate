package com.jpahybernatepractice.jpa_hybernate_practice.DTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto {
    Long id;
    String sku;
    String title;
    BigDecimal price;
    Integer quantity;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
