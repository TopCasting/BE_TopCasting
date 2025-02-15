package com.ll.topcastingbe.domain.order.dto.order.response;

import com.ll.topcastingbe.domain.order.dto.order_item.response.FindOrderProductResponseDto;
import com.ll.topcastingbe.domain.order.entity.Orders;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Builder
public record FindOrderResponse(UUID orderId,
                                String customerName,
                                String customerPhoneNumber,
                                String customerAddress,
                                String orderStatus,
                                LocalDateTime orderCreatedDate,
                                Long totalProductQuantity,
                                Long totalProductPrice,
                                List<FindOrderProductResponseDto> findOrderProductRespons) {

    public static FindOrderResponse of(final Orders order,
                                       final List<FindOrderProductResponseDto> findOrderProductRespons) {
        FindOrderResponse findOrdersResponse = FindOrderResponse.builder()
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
