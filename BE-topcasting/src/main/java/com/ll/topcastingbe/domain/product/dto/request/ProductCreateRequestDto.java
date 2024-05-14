package com.ll.topcastingbe.domain.product.dto.request;

import com.ll.topcastingbe.domain.option.dto.request.ItemCreateOptionRequestDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductCreateRequestDto {

    @NotBlank
    private String productName;
    @Positive
    @NotNull
    private BigDecimal productPrice;
    private List<ItemCreateOptionRequestDto> productColors;
    @NotBlank
    private String productImage;
    @NotBlank
    private String productDetailedImage;
    @NotNull
    private Long mainCategoryId;
    @NotNull
    private Long subCategoryId;

}
