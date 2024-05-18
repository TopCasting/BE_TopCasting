package com.ll.topcastingbe.domain.order.service.order_product;

import com.ll.topcastingbe.domain.member.entity.Member;
import com.ll.topcastingbe.domain.order.dto.order_item.request.AddOrderProductRequest;
import com.ll.topcastingbe.domain.order.dto.order_item.request.ModifyOrderProductRequest;
import com.ll.topcastingbe.domain.order.dto.order_item.response.FindOrderProductResponse;
import com.ll.topcastingbe.domain.order.entity.OrderProduct;
import com.ll.topcastingbe.domain.order.entity.Orders;
import java.util.List;
import java.util.UUID;

public interface OrderProductService {
    void addOrderProduct(final Orders order,
                         final AddOrderProductRequest addOrderProductRequest);

    List<FindOrderProductResponse> findAllByOrderId(final UUID orderId, final Member member);

    void updateOrderItem(final Long orderProductId,
                         final ModifyOrderProductRequest modifyOrderProductRequest,
                         final Member member);

    void removeAllByOrder(final Orders order);

    List<FindOrderProductResponse> findAllByOrderIdForAdmin(final UUID orderId);

    List<OrderProduct> findOrderProducts(final Orders order);

    List<OrderProduct> findOrderProductsWithPessimisticWriteLock(final Orders order);
}
