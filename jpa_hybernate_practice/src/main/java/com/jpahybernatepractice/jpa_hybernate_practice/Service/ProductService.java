package com.jpahybernatepractice.jpa_hybernate_practice.Service;


import com.jpahybernatepractice.jpa_hybernate_practice.DTO.ProductDto;
import com.jpahybernatepractice.jpa_hybernate_practice.Entity.ProductEntity;
import com.jpahybernatepractice.jpa_hybernate_practice.Repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
private  final ProductRepository productRepository;
private  final ModelMapper mapper;

    public ProductDto createProduct(ProductDto productDto) {
        //DTO -> EmpEntity conversion
        ProductEntity productEntity = mapper.map(productDto, ProductEntity.class);
        //Business Logic Adding
        // Save EmployeeEntity
        ProductEntity savedProduct=productRepository.save(productEntity);
        return mapper.map(savedProduct, ProductDto.class);
    }

    public List<ProductDto> getAllProductsDesc() {
        return productRepository
                .findAll(Sort.by(Sort.Direction.DESC, "price"))
                .stream()
                .map(product -> mapper.map(product, ProductDto.class))
                .toList();
    }

    public List<ProductDto> getProductsByTitle(String title) {
        List<ProductEntity> matchedProductByTitle = productRepository.findByTitle(title);
        if(matchedProductByTitle.isEmpty()){
            return List.of();
        }
        return matchedProductByTitle.stream()
                .map(prod -> mapper.map(prod, ProductDto.class))
                .toList();



//        return productRepository.findByTitle(title).stream()
//                .map(prod-> mapper.map(prod, ProductDto.class))
//                .toList();
    }

    public List<ProductDto> getProductsCreatedAfter(LocalDate date) {
        LocalDateTime startDate = date.atStartOfDay();
        return productRepository.findByCreatedAtAfter(startDate)
                .stream()
                .map(prod->mapper.map(prod, ProductDto.class))
                .toList();
    }

    public List<ProductDto> findByQuantityLessThanAndPriceGreaterThan(int quantity, BigDecimal price) {
        return productRepository.findByQuantityLessThanAndPriceGreaterThan(quantity, price)
                .stream()
                .map(prod->mapper.map(prod, ProductDto.class))
                .toList();
    }

    public List<ProductDto> getProductPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository
                .findByPriceBetween(minPrice, maxPrice)
                .stream()
                .map(prod->mapper.map(prod, ProductDto.class))
                .toList();
    }
}
