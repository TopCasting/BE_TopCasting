package com.ll.topcastingbe.domain.product.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductPriceUpdateRequestDto {
    @Positive
    @NotNull
    private BigDecimal productPrice;
}
