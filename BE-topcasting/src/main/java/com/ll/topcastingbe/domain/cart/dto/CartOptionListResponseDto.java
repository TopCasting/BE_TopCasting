package com.ll.topcastingbe.domain.cart.dto;

import com.ll.topcastingbe.domain.cart.entity.CartOption;
import com.ll.topcastingbe.domain.image.entity.MainImage;
import com.ll.topcastingbe.global.constant.ShippingConst;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@Slf4j
public class CartOptionListResponseDto {
    private List<CartOptionResponseDto> cartOptions;
    private BigDecimal shippingFee;
    private BigDecimal freeShippingCond;

    public static CartOptionListResponseDto toDto(List<CartOption> cartOptions, Map<Long, MainImage> mainImageMap) {
        List<CartOptionResponseDto> cartOptionResponseDtos = cartOptions.stream()
                .map(co -> CartOptionResponseDto.toDto(co, mainImageMap.get(co.getOption().getProduct().getId())))
                .toList();

        return new CartOptionListResponseDto(cartOptionResponseDtos, ShippingConst.SHIPPING_FEE,
                ShippingConst.FREE_SHIPPING_COND);
    }
}
