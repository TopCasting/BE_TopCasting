package com.ll.topcastingbe.domain.product.service;

import com.ll.topcastingbe.domain.category.entity.SubCategory;
import com.ll.topcastingbe.domain.category.exception.CategoryErrorMessage;
import com.ll.topcastingbe.domain.category.exception.CategoryNotExistException;
import com.ll.topcastingbe.domain.category.repository.SubCategoryRepository;
import com.ll.topcastingbe.domain.image.entity.DetailedImage;
import com.ll.topcastingbe.domain.image.entity.Image;
import com.ll.topcastingbe.domain.image.service.ImageService;
import com.ll.topcastingbe.domain.product.dto.request.ProductCreateRequestDto;
import com.ll.topcastingbe.domain.product.dto.request.ProductImageUpdateRequestDto;
import com.ll.topcastingbe.domain.product.dto.request.ProductNameUpdateRequestDto;
import com.ll.topcastingbe.domain.product.dto.request.ProductPriceUpdateRequestDto;
import com.ll.topcastingbe.domain.product.dto.response.ProductDetailResponseDto;
import com.ll.topcastingbe.domain.product.entity.Product;
import com.ll.topcastingbe.domain.product.exception.ProductNotExistException;
import com.ll.topcastingbe.domain.product.repository.ProductRepository;
import com.ll.topcastingbe.domain.option.dto.ItemDetailOptionResponseDto;
import com.ll.topcastingbe.domain.option.entity.Option;
import com.ll.topcastingbe.domain.option.repository.OptionRepository;
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
                .orElseThrow(() -> new ProductNotExistException());

        List<ItemDetailOptionResponseDto> optionDtos = optionRepository.findByProductId(product.getId())
                .stream()
                .map(ItemDetailOptionResponseDto::toDto)
                .toList();

        return ProductDetailResponseDto.toDto(product, optionDtos);

    }

    @Transactional
    public Long saveProduct(ProductCreateRequestDto productRequestDto) {

        //요청된 카테고리가 있는지 검증
        SubCategory subCategory = subCategoryRepository.findByMainCategoryIdAndSubcategoryId(
                        productRequestDto.getMainCategoryId(), productRequestDto.getSubCategoryId())
                .orElseThrow(() -> new CategoryNotExistException(CategoryErrorMessage.CATEGORY_NOT_EXIST));

        Image image = imageService.uploadImage(productRequestDto.getProductName(), productRequestDto.getProductImage());
        DetailedImage detailedImage = imageService.uploadDetailedImage(productRequestDto.getProductName(),
                productRequestDto.getProductDetailedImage());

        //아이템 엔티티 생성
        Product product = Product.builder()
                .productName(productRequestDto.getProductName())
                .productPrice(productRequestDto.getProductPrice())
                .image(image)
                .detailedImage(detailedImage)
                .mainCategory(subCategory.getParentCategory())
                .subCategory(subCategory)
                .build();
        Product createdProduct = productRepository.save(product);

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

        Image image = product.getImage();
        DetailedImage detailedImage = product.getDetailedImage();

        if (updateDto.hasImage()){
            Image newImage = imageService.uploadImage(product.getProductName(), updateDto.getProductImage());
            imageService.deleteImage(image);
            image = newImage;
        }

        if (updateDto.hasDetailedImage()){
            DetailedImage newDetailedImage = imageService.uploadDetailedImage(product.getProductName(),
                    updateDto.getProductDetailedImage());
            imageService.deleteImage(detailedImage);
            detailedImage = newDetailedImage;
        }

        product.changeImage(image,detailedImage);
    }
}
