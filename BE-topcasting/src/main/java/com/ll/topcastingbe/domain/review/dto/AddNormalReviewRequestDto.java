package com.ll.topcastingbe.domain.review.dto;


import lombok.Data;

@Data
public class AddNormalReviewRequestDto {
    private String writer;
    private Long productId;
    private String title;
    private String content;
    private int rating;
}
