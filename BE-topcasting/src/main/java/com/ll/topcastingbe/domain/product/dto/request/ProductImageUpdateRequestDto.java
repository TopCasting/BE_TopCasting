package com.ll.topcastingbe.domain.product.dto.request;

import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class ProductImageUpdateRequestDto {
    private String productImage;
    private String productDetailedImage;

    public boolean hasImage(){
        return StringUtils.hasText(productImage);
    }

    public boolean hasDetailedImage(){
        return StringUtils.hasText(productDetailedImage);
    }
}
