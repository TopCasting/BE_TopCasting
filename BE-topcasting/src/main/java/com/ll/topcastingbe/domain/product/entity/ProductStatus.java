package com.ll.topcastingbe.domain.product.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ProductStatus {
    SELLING("판매"), STOP_SELLING("판매중지");

    private final String message;

}
