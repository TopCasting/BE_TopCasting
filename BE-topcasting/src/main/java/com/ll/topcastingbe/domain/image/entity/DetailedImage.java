package com.ll.topcastingbe.domain.image.entity;

import com.ll.topcastingbe.domain.product.entity.Product;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.time.LocalDateTime;
import lombok.Builder;

@Entity
@DiscriminatorValue("DETAILED")
public class DetailedImage extends Image {
    public DetailedImage() {
    }

    @Builder

    public DetailedImage(Long id, Product product, String path, String imageName,
                         String fullName, LocalDateTime createdDate) {
        super(id, product, path, imageName, fullName, createdDate);
    }
}
