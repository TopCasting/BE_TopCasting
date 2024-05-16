package com.ll.topcastingbe.domain.cart.dto;

import com.ll.topcastingbe.domain.cart.entity.CartOption;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartOptionResponseDto {
    private Long cartOptionId;
    private String productImage;
    private String productName;
    private String productColor;
    private int productQuantity;
    private BigDecimal productPrice;

    public static CartOptionResponseDto toDto(CartOption cartOption) {
        return CartOptionResponseDto.builder()
                .cartOptionId(cartOption.getId())
                .productImage(cartOption.getOption().getProduct().getImage().getPath())
                .productName(cartOption.getOption().getProduct().getProductName())
                .productColor(cartOption.getOption().getColorName())
                .productQuantity(cartOption.getProductQuantity())
                .productPrice(cartOption.getOption().getProduct().getProductPrice()).build();
    }
}
