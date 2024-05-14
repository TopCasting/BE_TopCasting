package com.ll.topcastingbe.domain.product.repository;

import com.ll.topcastingbe.domain.product.entity.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    //list -> dto -> slice
    @Query("SELECT p FROM Product p " +
                   "WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> findListByProductNameIgnoreCase(String keyword, Pageable pageable);

    @Query("SELECT p FROM Product p JOIN FETCH p.image i")
    List<Product> findAllProducts(Pageable pageable);

    @Query("select p from Product p join fetch p.image i join fetch p.detailedImage di where p.id = :productId")
    Optional<Product> findByProductIdWithImageAndOption(@Param("productId") Long productId);

    @Query("SELECT p FROM Product p JOIN FETCH p.image i WHERE p.mainCategory.id = :mainCategoryId")
    List<Product> findAllProductsByMainCategory(@Param("mainCategoryId") Long mainCategoryId, Pageable pageable);

    @Query("SELECT p FROM Product p JOIN FETCH p.image i WHERE p.subCategory.id = :subCategoryId")
    List<Product> findAllProductsBySubCategory(@Param("subCategoryId") Long subCategoryId,
                                               Pageable pageable);

    Optional<Product> findByProductName(String productName);
}
