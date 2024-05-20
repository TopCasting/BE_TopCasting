package com.ll.topcastingbe.domain.image.repository;

import com.ll.topcastingbe.domain.image.entity.MainImage;
import com.ll.topcastingbe.domain.product.entity.Product;
import com.ll.topcastingbe.domain.product.repository.ProductRepository;
import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@Profile("test")
@SpringBootTest
class ImageRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ImageRepository imageRepository;

    @DisplayName("상품번호를 통해 메인이미지를 불러올 수 있다.")
    @Test
    void findMainImageByProductIdTest() {
        Product product = Product.builder()
                .productName("새로운 상품").build();
        Product savedProduct = productRepository.save(product);

        MainImage mainImage = MainImage.builder()
                .product(savedProduct)
                .imageName("새로운 이미지")
                .fullName("fullName")
                .path("path")
                .createdDate(LocalDateTime.now())
                .build();
        imageRepository.save(mainImage);

        MainImage result = imageRepository.findMainImageByProductId(savedProduct.getId());

        Assertions.assertThat(result.getProduct().getId()).isEqualTo(savedProduct.getId());

    }
}
