package com.jpahybernatepractice.jpa_hybernate_practice;

import com.jpahybernatepractice.jpa_hybernate_practice.Entity.ProductEntity;
import com.jpahybernatepractice.jpa_hybernate_practice.Repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class JpaHybernatePracticeApplicationTests {
	@Autowired
	ProductRepository productRepository;

	@Test
	void contextLoads() {
	}
	@Test
	void  testRepository(){
		ProductEntity productEntity= ProductEntity.builder()
				.sku("neshle111")
				.title("Nesle Chokolate")
				.price(BigDecimal.valueOf(123.55))
				.quantity(12)
				.build();

		ProductEntity savedProductEntity = productRepository.save(productEntity);
		System.out.println(savedProductEntity);
	}

	@Test
	void getRepositories(){
		List<ProductEntity> productEntityList = productRepository.findAll();
		for(ProductEntity p:productEntityList){
			System.out.println(p);
		}
	}

	@Test
	void getProductByItsTitle(){
		List<ProductEntity> productEntityList = productRepository.findByTitle("Parle Biscuit");
		for(ProductEntity p: productEntityList){
			System.out.println(p);
		}
	}


	@Test
	void getProductCreatedAfter(){
		List<ProductEntity> productEntityList= productRepository.findByCreatedAtAfter(
				LocalDateTime.of(2024, 01, 01, 0,0,0)
		);
		for(ProductEntity p: productEntityList){
			System.out.println(p);
		}
	}

	@Test
	void getProductUpdatedAfter(){
		List<ProductEntity> productEntityList= productRepository.findByUpdatedAtAfter(
				LocalDateTime.of(2024, 01, 01, 0,0,0)
		);
		for(ProductEntity p: productEntityList){
			System.out.println(p);
		}
	}

	@Test
	void getProductWithQuantityAndPrice(){
		List<ProductEntity> productEntityList= productRepository.findByQuantityLessThanAndPriceGreaterThan(
				10, BigDecimal.valueOf(7.95)
		);
		for(ProductEntity p: productEntityList){
			System.out.println(p);
		}
	}

	@Test
	void getProductWithTitleLikes(){
//		List<ProductEntity> productEntityList= productRepository.findByTitleLike("%Bis%");
		List<ProductEntity> productEntityList= productRepository.findByTitleContainsIgnoreCase("bis");
		for(ProductEntity p: productEntityList){
			System.out.println(p);
		}
	}

	@Test
	void getProductWithExactPriceAndQuantity(){
//		List<ProductEntity> productEntityList= productRepository.findByTitleLike("%Bis%");
		Optional<ProductEntity> productEntityList= productRepository.findByPriceAndQuantity(BigDecimal.valueOf(10.25), Integer.valueOf(6));
		productEntityList.ifPresent(System.out::println);
	}

	@Test
	void getDedicatedSku(){
		Optional<ProductEntity> product = productRepository.findBySku("omicron1145");
		product.ifPresentOrElse(
				System.out::println,
				()-> System.out.println("Product not found!!")
		);
	}

	@Test
	void getProductPriceBetween(){
		List<ProductEntity> productEntityList = productRepository.findByPriceBetween(BigDecimal.valueOf(9), BigDecimal.valueOf(12));
		productEntityList.forEach(System.out::println);
	}

@Test
	void getProductsInList(){
		List<ProductEntity> productEntityList = productRepository.findByQuantityIn(
				List.of(5, 10, 12)
		);
		productEntityList.forEach(System.out::println);
}

	@Test
	void getProductsOrderByPricesInDescOrder(){
		List<ProductEntity> productEntityList = productRepository.findByOrderByPriceDesc();
		productEntityList.forEach(System.out::println);
	}

	@Test
	void FindFirstFiveElementUsingPageable(){
		Page<ProductEntity> page=productRepository.findAll(
				PageRequest.of(0,5)
		);

		System.out.println("Total page: " + page.getTotalPages());
		System.out.println("Total Element: " + page.getTotalElements());

		page.getContent().forEach(System.out::println);
	}

	@Test
	void checkIfExists(){
		boolean exists = productRepository.existsBySku("omicron115");
		System.out.println("Is Product Exists ?  : "+ exists);
	}

	@Test
	void deleteProductBySku(){
		productRepository.deleteBySku("omicron115");

		Optional<ProductEntity> product =
				productRepository.findBySku("omicron115");

		assertTrue(product.isEmpty());
	}
}
