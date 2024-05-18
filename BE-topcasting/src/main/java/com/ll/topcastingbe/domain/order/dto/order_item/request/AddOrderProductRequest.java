package com.ll.topcastingbe.domain.order.dto.order_item.request;

import lombok.Builder;

@Builder
public record AddOrderProductRequest(Long cartProductId,
                                     Long productQuantity) {


}
