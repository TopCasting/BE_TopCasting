package com.ll.topcastingbe.domain.product.search.service;

import com.ll.topcastingbe.domain.product.search.dto.SearchProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

@SpringBootTest
class ProductSearchServiceTest {

    @Autowired
    private ProductSearchService productSearchService;

    @Test
    public void getItemByMainCategoryTest() {

        Long mainCategoryId = 2L; // 메인 카테고리 ID 설정
        Pageable pageable = PageRequest.of(0, 10); // 페이지 및 크기 설정

        Slice<SearchProductDto> content = productSearchService.getProductsByMainCategory(pageable, mainCategoryId);
        System.out.println(content.getContent());
    }

    @Test
    public void getItemBySubCategory() {
        Long subCategoryId = 1L;
        Pageable pageable = PageRequest.of(0, 10);

        Slice<SearchProductDto> content = productSearchService.getProductsBySubcategory(pageable, subCategoryId);
        System.out.println(content.getContent());
    }
}
