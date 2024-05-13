package com.ll.topcastingbe.domain.image.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.time.LocalDateTime;
import lombok.Builder;

@Entity
@DiscriminatorValue("DETAILED")
public class DetailedImage extends Image{
    public DetailedImage() {
    }

    @Builder
    public DetailedImage(Long id, String path, String imageName, String fullName, LocalDateTime createdDate) {
        super(id, path, imageName, fullName, createdDate);
    }
}
