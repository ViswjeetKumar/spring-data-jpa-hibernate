package com.jpahybernatepractice.jpa_hybernate_practice.Controller;

import com.jpahybernatepractice.jpa_hybernate_practice.DTO.GetTitlePriceOnly;
import com.jpahybernatepractice.jpa_hybernate_practice.DTO.IProductDto;
import com.jpahybernatepractice.jpa_hybernate_practice.DTO.ProductDto;
import com.jpahybernatepractice.jpa_hybernate_practice.Service.ProductPaginationService;
import com.jpahybernatepractice.jpa_hybernate_practice.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.PrivateKey;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {
private final ProductService productService;
private final ProductPaginationService productPaginationService;

private static final int PAGE_SIZE=5;

// Create Product.
@PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
    ProductDto product = productService.createProduct(productDto);
    return new ResponseEntity<>(product, HttpStatus.CREATED);
}

//Get all product shorted by given fields.
    @GetMapping("/sort")
    public ResponseEntity<List<ProductDto>> getFilteredData(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "id") String sortBy
    ){
    Page<ProductDto> sortedProducts = productService.getAllProductsSortedBy(page, PAGE_SIZE, sortBy );
    return ResponseEntity.ok(sortedProducts.getContent());
    }

// Get All Products.
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProductsDesc(){
    List<ProductDto> allProducts = productService.getAllProductsDesc();
    return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    //Get all product using Paination
    // PAGINATION API
    // localhost:8080/products/paged?page=0&size=3&sortBy=price&direction=desc
    @GetMapping("/paged")
    public Page<ProductDto> getAllProductPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ){
    return productPaginationService.getAllProducts(page, size, sortBy, direction);
    }

    //localhost:8080/products/title?title=Delta Treats
    @GetMapping("/title")
    public ResponseEntity<List<ProductDto>> getProductByTitle(
            @RequestParam String title
    ){
    List<ProductDto> productsByTitle = productService.getProductsByTitle(title);
    return ResponseEntity.ok(productsByTitle);
    }

    //localhost:8080/products/created-after?date=2025-01-01
@GetMapping("/created-after")
    public ResponseEntity<List<ProductDto>> getProductCreatedAfter(
            @RequestParam("date") LocalDate date
){
    List<ProductDto> products = productService.getProductsCreatedAfter(date);
    return ResponseEntity.ok(products);
}


//localhost:8080/products/filter?quantity=12&price=5.34
@GetMapping("/filter")
    public ResponseEntity<List<ProductDto>> getProductWithQuantityAndPrice(
        @RequestParam int quantity,
        @RequestParam BigDecimal price
        ){
    List<ProductDto> products = productService.findByQuantityLessThanAndPriceGreaterThan(quantity, price);
    return ResponseEntity.ok(products);
}


//localhost:8080/products/price-between?minPrice=10&maxPrice=14
@GetMapping("/price-between")
    public ResponseEntity<List<ProductDto>> getProductPriceBetween(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice
){
    List<ProductDto> productsBetween = productService.getProductPriceBetween(minPrice, maxPrice);
    return ResponseEntity.ok(productsBetween);
}

@GetMapping("/only")
    public ResponseEntity<List<IProductDto>> getFewDetails(){
    return ResponseEntity.ok(productService.getFewDetails());
}

@GetMapping("/getTitlePrice")
    public ResponseEntity<Page<GetTitlePriceOnly>> getTitlePriceAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "title") String sortedBy,
            @RequestParam(defaultValue = "desc") String orderIn
){
    return new ResponseEntity<>(productService.getTitlePriceAll(page, size, sortedBy, orderIn), HttpStatus.OK);
}

}
