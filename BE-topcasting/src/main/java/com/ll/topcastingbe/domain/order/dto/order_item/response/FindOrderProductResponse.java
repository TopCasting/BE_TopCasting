package com.ll.topcastingbe.domain.order.dto.order_item.response;

import com.ll.topcastingbe.domain.order.entity.OrderProduct;
import java.util.List;
import lombok.Builder;

@Builder
public record FindOrderProductResponse(String productImagePath,
                                       String productName,
                                       String productOption,
                                       Long productQuantity,
                                       Long totalPrice
) {

    public static FindOrderProductResponse of(final OrderProduct orderProduct) {
        final FindOrderProductResponse findOrderProductResponse = FindOrderProductResponse.builder()
                .productImagePath(orderProduct.getProductImagePath())
                .productName(orderProduct.getProductName())
                .productOption(orderProduct.getOption().getColorName())
                .productQuantity(orderProduct.getProductQuantity())
                .totalPrice(orderProduct.getTotalPrice())
                .build();
        return findOrderProductResponse;
    }

    public static List<FindOrderProductResponse> ofList(final List<OrderProduct> orderProducts) {
        final List<FindOrderProductResponse> findorderProductRespons = orderProducts.stream()
                .map(FindOrderProductResponse::of)
                .toList();
        return findorderProductRespons;
    }
}
