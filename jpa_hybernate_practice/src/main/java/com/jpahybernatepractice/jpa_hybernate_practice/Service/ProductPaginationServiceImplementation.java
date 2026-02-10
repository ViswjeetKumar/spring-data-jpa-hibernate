package com.jpahybernatepractice.jpa_hybernate_practice.Service;

import com.jpahybernatepractice.jpa_hybernate_practice.DTO.ProductDto;
import com.jpahybernatepractice.jpa_hybernate_practice.Entity.ProductEntity;
import com.jpahybernatepractice.jpa_hybernate_practice.Repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductPaginationServiceImplementation implements ProductPaginationService{
    private  final ModelMapper mapper;
    private final ProductRepository productRepository;
    @Override
    public Page<ProductDto> getAllProducts(
            int page,
            int size,
            String shortBy,
            String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(shortBy).descending() : Sort.by(shortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ProductEntity> productPage = productRepository.findAll(pageable);
        // Convert Entity → DTO (PRO WAY)
        return productPage
                .map(prod->mapper.map(prod, ProductDto.class));
    }

}
