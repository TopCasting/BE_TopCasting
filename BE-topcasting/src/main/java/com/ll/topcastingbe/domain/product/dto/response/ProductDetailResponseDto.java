package com.ll.topcastingbe.domain.product.dto.response;

import com.ll.topcastingbe.domain.product.entity.Product;
import com.ll.topcastingbe.domain.option.dto.ItemDetailOptionResponseDto;
import java.math.BigDecimal;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDetailResponseDto {

    private Long productId;
    private String productName;
    private BigDecimal productPrice;
    private List<ItemDetailOptionResponseDto> productColors;
    private String productImageUrl;
    private String productDetailedImageUrl;
    private Long mainCategoryId;
    private Long subCategoryId;

    public static ProductDetailResponseDto toDto(Product product, List<ItemDetailOptionResponseDto> optionDtos) {
        return ProductDetailResponseDto.builder()
                .productId(product.getId())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .productColors(optionDtos)
                .productImageUrl(product.getImage().getPath())
                .productDetailedImageUrl(product.getDetailedImage().getPath())
                .mainCategoryId(product.getMainCategory().getId())
                .subCategoryId(product.getSubCategory().getId())
                .build();
    }
}
