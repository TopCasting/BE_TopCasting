package com.ll.topcastingbe.domain.product.search.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class SearchProductDto {
    private Long productId;
    private String productName;
    private BigDecimal productPrice;
    private String imageUrl;
    private Long totalOrderedCount;
}
