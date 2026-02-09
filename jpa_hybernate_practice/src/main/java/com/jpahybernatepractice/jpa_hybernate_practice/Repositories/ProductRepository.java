package com.jpahybernatepractice.jpa_hybernate_practice.Repositories;

import com.jpahybernatepractice.jpa_hybernate_practice.Entity.ProductEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByTitle(String title);

    List<ProductEntity> findByCreatedAtAfter(LocalDateTime time);

    List<ProductEntity> findByUpdatedAtAfter(LocalDateTime updateTime);

    List<ProductEntity> findByQuantityLessThanAndPriceGreaterThan(int quantity, BigDecimal price);

    List<ProductEntity> findByTitleLike(String titleLike);
    List<ProductEntity> findByTitleContainsIgnoreCase(String titleContains);

    @Query("select p from ProductEntity p where p.price=:price and p.quantity=:quantity ")
    Optional<ProductEntity> findByPriceAndQuantity(BigDecimal price, Integer quantity);

    Optional<ProductEntity> findBySku(String sku);

    List<ProductEntity> findByPriceBetween(BigDecimal min , BigDecimal max);

    List<ProductEntity> findByQuantityIn(List<Integer> quantities);

    List<ProductEntity> findByOrderByPriceDesc();

    Page<ProductEntity> findAll(Pageable pageable);

    Boolean existsBySku(String sku);

    @Modifying
    @Transactional
    @Query("delete from ProductEntity p where sku=:sku ")
    void deleteBySku(String sku);
}
