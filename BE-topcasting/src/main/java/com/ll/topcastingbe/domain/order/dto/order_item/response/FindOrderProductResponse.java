package com.ll.topcastingbe.domain.order.dto.order_item.response;

import com.ll.topcastingbe.domain.image.entity.MainImage;
import com.ll.topcastingbe.domain.order.entity.OrderProduct;
import java.util.List;
import java.util.Map;
import lombok.Builder;

@Builder
public record FindOrderProductResponse(String productImagePath,
                                       String productName,
                                       String productOption,
                                       Long productQuantity,
                                       Long totalPrice
) {

    public static FindOrderProductResponse of(OrderProduct orderProduct, MainImage mainImage) {
        final FindOrderProductResponse findOrderProductResponse = FindOrderProductResponse.builder()
                .productImagePath(mainImage.getPath())
                .productName(orderProduct.getProductName())
                .productOption(orderProduct.getOption().getColorName())
                .productQuantity(orderProduct.getProductQuantity())
                .totalPrice(orderProduct.getTotalPrice())
                .build();
        return findOrderProductResponse;
    }

    public static List<FindOrderProductResponse> ofList(final List<OrderProduct> orderProducts,
                                                        Map<Long, MainImage> mainImageMap) {
        final List<FindOrderProductResponse> findorderProductRespons = orderProducts.stream()
                .map(op -> of(op, mainImageMap.get(op.getOption().getProduct().getId())))
                .toList();
        return findorderProductRespons;
    }
}
