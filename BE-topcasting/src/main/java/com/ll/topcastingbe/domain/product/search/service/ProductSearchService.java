package com.ll.topcastingbe.domain.product.search.service;

import com.ll.topcastingbe.domain.product.entity.Product;
import com.ll.topcastingbe.domain.product.repository.ProductRepository;
import com.ll.topcastingbe.domain.product.search.dto.SearchProductDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductSearchService {

    private final ProductRepository productRepository;
    
    public Page<SearchProductDto> productsSearch(String keyword, Pageable pageable) {
        List<Product> productList = productRepository.findListByProductNameIgnoreCase(keyword, pageable);
        return createPage(productList, pageable);
    }

    public Page<SearchProductDto> getProducts(Pageable pageable) {
        List<Product> productList = productRepository.findAllProducts(pageable);
        return createPage(productList, pageable);
    }

    public Page<SearchProductDto> getProductsByMainCategory(Pageable pageable, Long mainCategoryId) {
        List<Product> productList = productRepository.findAllProductsByMainCategory(mainCategoryId, pageable);
        return createPage(productList, pageable);
    }

    public Page<SearchProductDto> getProductsBySubcategory(Pageable pageable,
                                                            Long subCategoryId) {
        List<Product> productList = productRepository.findAllProductsBySubCategory(subCategoryId, pageable);
        return createPage(productList, pageable);
    }

    public Page<SearchProductDto> sortSearch(Pageable pageable) {
        return getProducts(pageable);
    }

    public Page<SearchProductDto> sortMainCategory(Pageable pageable, Long mainCategoryId) {
        return getProductsByMainCategory(pageable, mainCategoryId);
    }

    public Page<SearchProductDto> sortSubCategory(Pageable pageable, Long subCategoryId) {
        return getProductsBySubcategory(pageable, subCategoryId);
    }

    private Page<SearchProductDto> createPage(List<Product> productList, Pageable pageable) {
        List<SearchProductDto> productDtoList = mapToSearchProductList(productList);

        return new PageImpl<>(productDtoList, pageable, productList.size());
    }

    private List<SearchProductDto> mapToSearchProductList(List<Product> productList) {
        return productList.stream()
                .map(product -> {
                    SearchProductDto searchProductDto = new SearchProductDto();
                    searchProductDto.setProductId(product.getId());
                    searchProductDto.setProductName(product.getProductName());
                    searchProductDto.setProductPrice(product.getProductPrice());
                    searchProductDto.setImageUrl(product.getImage().getPath());
                    return searchProductDto;
                })
                .collect(Collectors.toList());
    }
}

