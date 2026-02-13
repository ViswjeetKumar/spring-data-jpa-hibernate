package com.jpahybernatepractice.jpa_hybernate_practice.Service;


import com.jpahybernatepractice.jpa_hybernate_practice.DTO.GetTitlePriceOnly;
import com.jpahybernatepractice.jpa_hybernate_practice.DTO.IProductDto;
import com.jpahybernatepractice.jpa_hybernate_practice.DTO.ProductDto;
import com.jpahybernatepractice.jpa_hybernate_practice.Entity.ProductEntity;
import com.jpahybernatepractice.jpa_hybernate_practice.Repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public Page<ProductDto> getAllProductsSortedBy(int page, int PAGE_SIZE, String sortBy) {
        Sort sort = Sort.by(Sort.Order.desc(sortBy),
                Sort.Order.desc("price"),
                Sort.Order.desc("quantity")
                );
        Pageable pageable = PageRequest.of(page, PAGE_SIZE, sort);
        Page<ProductEntity> productPage = productRepository.findAll(pageable);

//        List<ProductEntity> products = productRepository.findBy(Sort.by(Sort.Direction.DESC, sortBy, "price", "quantity"));
//        List<ProductEntity> products = productRepository.findBy(
//                Sort.by(Sort.Order.desc(sortBy),
//                        Sort.Order.desc("price"),
//                        Sort.Order.desc("quantity")
//                ));
        return productPage
                .map(prod->mapper.map(prod, ProductDto.class));
    }

    public List<IProductDto> getFewDetails() {
        return productRepository.findBy();
    }

    public Page<GetTitlePriceOnly> getTitlePriceAll(
            int page,
            int size,
            String sortedBy,
            String orderIn
    ) {
        Sort sort = Sort.by(Sort.Order.desc(sortedBy),
                Sort.Order.desc("price")
                );

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<ProductEntity> pageList = productRepository.findAll(pageable);
        // Entity to DTO Conversion
//        List<GetTitlePriceOnly> DtoPage = pageList
//                .getContent()
//                .stream()
//                .map(prod-> new GetTitlePriceOnly(
//                        prod.getTitle(),
//                        prod.getPrice()
//                ))
//                .collect(Collectors.toList());
//        );

        return pageList.map(prod-> new GetTitlePriceOnly(
                prod.getTitle(),
                prod.getPrice()
        ));

    }
}
