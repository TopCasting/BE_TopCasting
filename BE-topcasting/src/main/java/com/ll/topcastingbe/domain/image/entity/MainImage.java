package com.ll.topcastingbe.domain.image.entity;

import com.ll.topcastingbe.domain.product.entity.Product;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.time.LocalDateTime;
import lombok.Builder;

@Entity
@DiscriminatorValue("MAIN")
public class MainImage extends Image {
    public MainImage() {
    }

    @Builder
    public MainImage(Long id, Product product, String path, String imageName,
                     String fullName, LocalDateTime createdDate) {
        super(id, product, path, imageName, fullName, createdDate);
    }
}
