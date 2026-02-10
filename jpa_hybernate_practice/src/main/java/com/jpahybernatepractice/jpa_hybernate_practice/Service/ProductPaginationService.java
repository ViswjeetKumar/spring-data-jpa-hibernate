package com.jpahybernatepractice.jpa_hybernate_practice.Service;

import com.jpahybernatepractice.jpa_hybernate_practice.DTO.ProductDto;
import org.springframework.data.domain.Page;

public interface ProductPaginationService {
    Page<ProductDto> getAllProducts(
            int page,
            int size,
            String shortBy,
            String direction
    );
}
