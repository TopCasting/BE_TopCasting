package com.ll.topcastingbe.domain.cart.dto;

import com.ll.topcastingbe.global.constant.ShippingConst;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class CartOptionListResponseDto {
    private final List<CartOptionResponseDto> cartOptions;
    private final BigDecimal shippingFee;
    private final BigDecimal freeShippingCond;

    public static CartOptionListResponseDto toDto(List<CartOptionResponseDto> cartOptionResponseDtos) {
        return new CartOptionListResponseDto(cartOptionResponseDtos, ShippingConst.SHIPPING_FEE,
                ShippingConst.FREE_SHIPPING_COND);
    }
}
