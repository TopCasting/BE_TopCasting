package com.ll.topcastingbe.domain.order.dto.order_item.response;

import com.ll.topcastingbe.domain.image.entity.MainImage;
import com.ll.topcastingbe.domain.order.entity.OrderOption;
import lombok.Builder;
import lombok.Data;

@Data
public class FindOrderProductResponseDto {

    private final String productImagePath;
    private final String productName;
    private final String productOption;
    private final Long productQuantity;
    private final Long totalPrice;

    @Builder
    public FindOrderProductResponseDto(String productImagePath, String productName, String productOption,
                                       Long productQuantity, Long totalPrice) {
        this.productImagePath = productImagePath;
        this.productName = productName;
        this.productOption = productOption;
        this.productQuantity = productQuantity;
        this.totalPrice = totalPrice;
    }

    public static FindOrderProductResponseDto of(OrderOption orderOption, MainImage mainImage) {
        return FindOrderProductResponseDto.builder()
                .productImagePath(mainImage.getPath())
                .productName(orderOption.getProductName())
                .productOption(orderOption.getOption().getColorName())
                .productQuantity(orderOption.getProductQuantity())
                .totalPrice(orderOption.getTotalPrice())
                .build();
    }

}
