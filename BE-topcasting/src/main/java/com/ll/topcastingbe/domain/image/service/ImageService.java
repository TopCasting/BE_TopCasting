package com.ll.topcastingbe.domain.image.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ll.topcastingbe.domain.image.entity.DetailedImage;
import com.ll.topcastingbe.domain.image.entity.Image;
import com.ll.topcastingbe.domain.image.entity.MainImage;
import com.ll.topcastingbe.domain.image.repository.ImageRepository;
import com.ll.topcastingbe.domain.product.entity.Product;
import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.UUID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ImageService {

    private final AmazonS3 amazonS3;
    private final ImageRepository imageRepository;

    @Value("${aws.s3.bucket}")
    private String bucket;

    @Transactional
    public Image uploadImage(String itemName, String base64, Product product) {

        ImageUploadDto imageUploadDto = createImageUploadDto(itemName, base64);

        MainImage mainImage = MainImage.builder()
                .path(imageUploadDto.getImageUrl())
                .product(product)
                .imageName(imageUploadDto.getImageName())
                .fullName(imageUploadDto.getFullName())
                .createdDate(imageUploadDto.getCreatedDate())
                .build();

        return imageRepository.save(mainImage);
    }

    @Transactional
    public DetailedImage uploadDetailedImage(String itemName, String base64, Product product) {

        ImageUploadDto imageUploadDto = createImageUploadDto(itemName, base64);

        DetailedImage detailedImage = DetailedImage.builder()
                .path(imageUploadDto.getImageUrl())
                .product(product)
                .imageName(imageUploadDto.getImageName())
                .fullName(imageUploadDto.getFullName())
                .createdDate(imageUploadDto.getCreatedDate())
                .build();

        return imageRepository.save(detailedImage);
    }

    private ImageUploadDto createImageUploadDto(String itemName, String base64) {

        byte[] decodedFile = Base64.getMimeDecoder().decode(base64.substring(base64.indexOf(",") + 1));
        String contentType = base64.substring(base64.indexOf(":"), base64.indexOf(";"));

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(decodedFile.length);
        metadata.setContentType(contentType);

        //S3에 '년/월/일/UUID_파일이름' 으로 저장
        LocalDate now = LocalDate.now();
        String datePath = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd/"));
        String imageName = UUID.randomUUID() + "_" + itemName;
        String fullName = datePath + imageName;

        amazonS3.putObject(
                new PutObjectRequest(bucket, fullName, new ByteArrayInputStream(decodedFile), metadata));
        String imageUrl = amazonS3.getUrl(bucket, fullName).toString();

        return new ImageUploadDto(imageUrl, imageName, fullName, LocalDateTime.now());
    }

    @Transactional
    public void deleteImage(Image image) {

        amazonS3.deleteObject(bucket, image.getFullName());

        imageRepository.delete(image);
    }

    public MainImage findMainImage(Product product) {
        return imageRepository.findMainImageByProductId(product.getId());
    }

    public DetailedImage findDetailedImage(Product product) {
        return imageRepository.findDetailedImageByProductId(product.getId());
    }

    @Getter
    private static class ImageUploadDto {
        private final String imageUrl;
        private final String imageName;
        private final String fullName;

        private final LocalDateTime createdDate;

        public ImageUploadDto(String imageUrl, String imageName, String fullName, LocalDateTime createdDate) {
            this.imageUrl = imageUrl;
            this.imageName = imageName;
            this.fullName = fullName;
            this.createdDate = createdDate;
        }

    }
}
