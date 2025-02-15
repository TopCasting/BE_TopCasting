package com.ll.topcastingbe.domain.order.dto.order.request;

import com.ll.topcastingbe.domain.member.entity.Member;
import com.ll.topcastingbe.domain.order.dto.order_item.request.AddOrderProductRequest;
import com.ll.topcastingbe.domain.order.entity.OrderStatus;
import com.ll.topcastingbe.domain.order.entity.Orders;
import java.util.List;
import lombok.Builder;

@Builder
public record AddOrderRequest(String customerName,
                              String customerAddress,
                              String customerPhoneNumber,
                              Long totalProductQuantity,
                              Long totalProductPrice,
                              List<AddOrderProductRequest> addOrderProductRequest) {

    public Orders toOrder(final Member member) {
        Orders orders = Orders.builder()
                .member(member)
                .orderStatus(OrderStatus.WAITING)
                .customerName(customerName)
                .customerAddress(customerAddress)
                .customerPhoneNumber(customerPhoneNumber)
                .totalProductQuantity(totalProductQuantity)
                .totalProductQuantity(totalProductPrice)
                .build();

        return orders;
    }
}
