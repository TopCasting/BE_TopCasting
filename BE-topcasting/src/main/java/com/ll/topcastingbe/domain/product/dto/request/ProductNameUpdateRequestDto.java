package com.ll.topcastingbe.domain.product.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductNameUpdateRequestDto {
    @NotBlank
    private String productName;
}
