package com.ll.topcastingbe.domain.product.service;

import com.ll.topcastingbe.domain.category.entity.SubCategory;
import com.ll.topcastingbe.domain.category.repository.SubCategoryRepository;
import com.ll.topcastingbe.domain.image.entity.DetailedImage;
import com.ll.topcastingbe.domain.image.entity.Image;
import com.ll.topcastingbe.domain.image.entity.MainImage;
import com.ll.topcastingbe.domain.image.service.ImageService;
import com.ll.topcastingbe.domain.option.dto.ItemDetailOptionResponseDto;
import com.ll.topcastingbe.domain.option.entity.Option;
import com.ll.topcastingbe.domain.option.repository.OptionRepository;
import com.ll.topcastingbe.domain.product.dto.request.ProductCreateRequestDto;
import com.ll.topcastingbe.domain.product.dto.request.ProductImageUpdateRequestDto;
import com.ll.topcastingbe.domain.product.dto.request.ProductNameUpdateRequestDto;
import com.ll.topcastingbe.domain.product.dto.request.ProductPriceUpdateRequestDto;
import com.ll.topcastingbe.domain.product.dto.response.ProductDetailResponseDto;
import com.ll.topcastingbe.domain.product.entity.Product;
import com.ll.topcastingbe.global.exception.product.ProductNotExistException;
import com.ll.topcastingbe.domain.product.repository.ProductRepository;
import com.ll.topcastingbe.global.exception.category.CategoryNotExistException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final OptionRepository optionRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final ImageService imageService;

    public ProductDetailResponseDto findProduct(Long productId) {
        Product product = productRepository.findByProductIdWithImageAndOption(productId)
                .orElseThrow(ProductNotExistException::new);

        List<ItemDetailOptionResponseDto> optionDtos = optionRepository.findByProductId(product.getId())
                .stream()
                .map(ItemDetailOptionResponseDto::toDto)
                .toList();

        MainImage mainImage = imageService.findMainImage(product);
        DetailedImage detailedImage = imageService.findDetailedImage(product);

        return ProductDetailResponseDto.toDto(product, optionDtos, mainImage, detailedImage);

    }

    @Transactional
    public Long saveProduct(ProductCreateRequestDto productRequestDto) {

        //요청된 카테고리가 있는지 검증
        SubCategory subCategory = subCategoryRepository.findByMainCategoryIdAndSubcategoryId(
                        productRequestDto.getMainCategoryId(), productRequestDto.getSubCategoryId())
                .orElseThrow(() -> new CategoryNotExistException());

        //아이템 엔티티 생성
        Product product = Product.builder()
                .productName(productRequestDto.getProductName())
                .productPrice(productRequestDto.getProductPrice())
                .mainCategory(subCategory.getParentCategory())
                .subCategory(subCategory)
                .build();
        Product createdProduct = productRepository.save(product);

        //이미지 엔티티 생성
        Image image = imageService.uploadImage(productRequestDto.getProductName(), productRequestDto.getProductImage(),
                createdProduct);
        DetailedImage detailedImage = imageService.uploadDetailedImage(productRequestDto.getProductName(),
                productRequestDto.getProductDetailedImage(), createdProduct);

        //옵션 엔티티 생성
        productRequestDto.getProductColors()
                .forEach(o -> optionRepository.save(Option.builder()
                        .colorName(o.getColorName())
                        .stock(o.getStock())
                        .product(product)
                        .build())
                );

        return createdProduct.getId();
    }

    @Transactional
    public void modifyProductName(Long productId, ProductNameUpdateRequestDto updateDto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(ProductNotExistException::new);

        product.changeProductName(updateDto.getProductName());
    }

    @Transactional
    public void modifyItemPrice(Long itemId, ProductPriceUpdateRequestDto updateDto) {
        Product product = productRepository.findById(itemId)
                .orElseThrow(ProductNotExistException::new);

        product.changeProductPrice(updateDto.getProductPrice());
    }

    @Transactional
    public void modifyItemImage(Long itemId, ProductImageUpdateRequestDto updateDto) {
        Product product = productRepository.findById(itemId)
                .orElseThrow(ProductNotExistException::new);

        MainImage mainImage = imageService.findMainImage(product);
        DetailedImage detailedImage = imageService.findDetailedImage(product);

        if (updateDto.hasImage()) {
            imageService.uploadImage(product.getProductName(), updateDto.getProductImage(), product);
            imageService.deleteImage(mainImage);
        }

        if (updateDto.hasDetailedImage()) {
            imageService.uploadDetailedImage(product.getProductName(),
                    updateDto.getProductDetailedImage(), product);
            imageService.deleteImage(detailedImage);
        }
    }
}
