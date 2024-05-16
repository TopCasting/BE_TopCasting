package com.ll.topcastingbe.domain.image.repository;

import com.ll.topcastingbe.domain.image.entity.DetailedImage;
import com.ll.topcastingbe.domain.image.entity.Image;
import com.ll.topcastingbe.domain.image.entity.MainImage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query("select i from MainImage i where i.product.id = :productId")
    MainImage findMainImageByProductId(Long productId);


    @Query("select i from DetailedImage i where i.product.id = :productId")
    DetailedImage findDetailedImageByProductId(Long productId);

    @Query("select i from MainImage i where i.product.id in :productNumbers")
    List<MainImage> findAllMainImageByProductNumberIn(@Param("productNumbers") List<Long> productNumbers);
}

