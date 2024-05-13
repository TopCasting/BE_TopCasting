package com.ll.topcastingbe.domain.product.controller;

import com.ll.topcastingbe.domain.product.dto.request.ProductCreateRequestDto;
import com.ll.topcastingbe.domain.product.dto.request.ProductImageUpdateRequestDto;
import com.ll.topcastingbe.domain.product.dto.request.ProductNameUpdateRequestDto;
import com.ll.topcastingbe.domain.product.dto.request.ProductPriceUpdateRequestDto;
import com.ll.topcastingbe.domain.product.dto.response.ProductDetailResponseDto;
import com.ll.topcastingbe.domain.product.search.dto.SearchProductDto;
import com.ll.topcastingbe.domain.product.search.service.ProductSearchService;
import com.ll.topcastingbe.domain.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@Slf4j
public class ProductController {
    private final ProductSearchService productSearchService;
    private final ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<?> productDetails(@PathVariable Long productId) {
        ProductDetailResponseDto productDto = productService.findProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body(productDto);
    }

    @PreAuthorize("hasRole('ADMIN')") //상품 추가는 관리자만 가능
    @PostMapping
    public ResponseEntity<?> productAdd(@RequestBody @Valid ProductCreateRequestDto productRequestDto) {

        log.info("productRequestDto={}", productRequestDto);
        log.info("productColors={}", productRequestDto.getProductColors());

        Long productId = productService.saveProduct(productRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }

    /*@PreAuthorize("hasRole('ADMIN')") //상품 삭제는 관리자만 가능
    @DeleteMapping("/{productId}")
    public ResponseEntity<?> itemRemove(){

    }*/

    @GetMapping("")
    public ResponseEntity<?> productsList(Pageable pageable) {
        Slice<SearchProductDto> productList = productSearchService.getProducts(pageable);
        return ResponseEntity.ok().body(productList);
    }


    @GetMapping(params = "keyword")
    public ResponseEntity<?> searchProducts(@RequestParam(value = "keyword") String keyword,
                                         Pageable pageable) {
        Slice<SearchProductDto> searchResult = productSearchService.productsSearch(keyword, pageable);
        return ResponseEntity.ok().body(searchResult);
    }

    @GetMapping(params = "sort")
    public ResponseEntity<?> searchSort(Pageable pageable) {
        Slice<SearchProductDto> sortProductResult = productSearchService.sortSearch(pageable);
        return ResponseEntity.ok().body(sortProductResult);
    }

    @GetMapping(params = "maincategory")
    public ResponseEntity<?> mainCategoryProducts(@RequestParam(value = "maincategory") Long id,
                                               Pageable pageable) {
        Slice<SearchProductDto> mainCategoryProduct = productSearchService.getProductsByMainCategory(pageable, id);
        return ResponseEntity.ok().body(mainCategoryProduct);
    }

    @GetMapping(params = "subcategory")
    public ResponseEntity<?> subCategoryProducts(@RequestParam(value = "subcategory") Long id,
                                              Pageable pageable) {
        Slice<SearchProductDto> subCategoryProduct = productSearchService.getProductsBySubcategory(pageable, id);
        return ResponseEntity.ok().body(subCategoryProduct);
    }

    @PreAuthorize("hasRole('ADMIN')") //아이템 이름 변경은 관리자만 가능
    @PatchMapping("/{productId}/productName")
    public ResponseEntity<?> productNameModify(@PathVariable Long productId,
                                               @RequestBody @Valid ProductNameUpdateRequestDto updateDto) {
        productService.modifyProductName(productId, updateDto);
        return ResponseEntity.ok(null);
    }

    @PreAuthorize("hasRole('ADMIN')") //아이템 이름 변경은 관리자만 가능
    @PatchMapping("/{productId}/productPrice")
    public ResponseEntity<?> productPriceModify(@PathVariable Long productId,
                                             @RequestBody @Valid ProductPriceUpdateRequestDto updateDto) {
        productService.modifyItemPrice(productId, updateDto);
        return ResponseEntity.ok(null);
    }

    @PreAuthorize("hasRole('ADMIN')") //아이템 이미지 변경은 관리자만 가능
    @PatchMapping("/{productId}/productImage")
    public ResponseEntity<?> productImageModify(@PathVariable Long productId,
                                             @RequestBody ProductImageUpdateRequestDto updateDto) {

        //Todo: Exception 고민해보기
        //이미지와 상세이미지 모두 없다면 예외처리
        if (!updateDto.hasImage() && !updateDto.hasDetailedImage()) {
            throw new IllegalArgumentException();
        }

        productService.modifyItemImage(productId, updateDto);
        return ResponseEntity.ok(null);
    }

}
