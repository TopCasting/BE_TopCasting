package com.ll.topcastingbe.domain.order.dto.order_item;

import com.ll.topcastingbe.domain.order.dto.order_item.request.AddOrderProductRequest;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AddOrderProductDto(@NotNull(message = "cartItem의 값을 필수입니다.") Long cartProductId,
                                 @Min(value = 1, message = "상품의 갯수는 1이상이어야 합니다.")
                              @NotNull(message = "상품의 갯수는 필수입니다.") Long productQuantity) {

    public AddOrderProductRequest toOrderProductDto() {
        final AddOrderProductRequest addOrderProductRequest = AddOrderProductRequest.builder()
                .cartProductId(cartProductId)
                .productQuantity(productQuantity)
                .build();

        return addOrderProductRequest;
    }
}
