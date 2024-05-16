package com.ll.topcastingbe.domain.order.dto.order.response;

import com.ll.topcastingbe.domain.order.dto.order_item.response.FindOrderProductResponse;
import com.ll.topcastingbe.domain.order.entity.Orders;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Builder
public record FindOrderForAdminResponse(UUID orderId,
                                        String paymentKey,
                                        String customerName,
                                        String customerPhoneNumber,
                                        String customerAddress,
                                        String orderStatus,
                                        LocalDateTime orderCreatedDate,
                                        Long totalProductQuantity,
                                        Long totalProductPrice,
                                        List<FindOrderProductResponse> findOrderProductRespons) {

    public static FindOrderForAdminResponse of(final Orders order,
                                               final List<FindOrderProductResponse> findOrderProductRespons,
                                               final String paymentKey) {
        FindOrderForAdminResponse findOrdersResponse = FindOrderForAdminResponse.builder()
                .paymentKey(paymentKey)
                .orderId(order.getId())
                .customerName(order.getCustomerName())
                .customerPhoneNumber(order.getCustomerPhoneNumber())
                .customerAddress(order.getCustomerAddress())
                .orderStatus(order.getOrderStatus().toString())
                .totalProductQuantity(order.getTotalProductQuantity())
                .totalProductPrice(order.getTotalProductPrice())
                .findOrderProductRespons(findOrderProductRespons)
                .build();

        return findOrdersResponse;
    }
}
