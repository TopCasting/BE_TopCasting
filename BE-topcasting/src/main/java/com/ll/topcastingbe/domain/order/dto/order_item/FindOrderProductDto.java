package com.ll.topcastingbe.domain.order.dto.order_item;

import com.ll.topcastingbe.domain.order.dto.order_item.response.FindOrderProductResponse;
import java.util.List;
import lombok.Builder;

@Builder
public record FindOrderProductDto(String productImagePath,
                                  String productName,
                                  String productOption,
                                  Long productQuantity,
                                  Long totalPrice) {

    public static FindOrderProductDto of(final FindOrderProductResponse orderProductResponse) {
        final FindOrderProductDto findOrderProductDto = FindOrderProductDto.builder()
                .productImagePath(orderProductResponse.productImagePath())
                .productName(orderProductResponse.productName())
                .productOption(orderProductResponse.productOption())
                .productQuantity(orderProductResponse.productQuantity())
                .totalPrice(orderProductResponse.totalPrice())
                .build();
        return findOrderProductDto;
    }

    public static List<FindOrderProductDto> ofList(final List<FindOrderProductResponse> orderProducts) {
        final List<FindOrderProductDto> findOrderProductDtos = orderProducts.stream()
                .map(FindOrderProductDto::of)
                .toList();
        return findOrderProductDtos;
    }
}
