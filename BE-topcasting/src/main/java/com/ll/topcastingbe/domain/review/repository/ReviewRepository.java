package com.ll.topcastingbe.domain.review.repository;

import com.ll.topcastingbe.domain.member.entity.Member;
import com.ll.topcastingbe.domain.order.entity.OrderOption;
import com.ll.topcastingbe.domain.review.entity.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select r from Review r where r.orderOption.option.product.id = :productId and r.rating = :rating")
    List<Review> findByRatingAndProductId(@Param("rating") int rating, @Param("productId") Long productId);


    List<Review> findByWriter(Member member);

    @Query("select r from Review r where r.orderOption.option.product.id =:productId order by r.rating desc ")
    List<Review> findByProductIdOrderByRatingDesc(@Param("productId") Long productId);

    @Query("select r from Review r where r.orderOption.option.product.id =:productId order by r.rating ASC ")
    List<Review> findByProductIdOrderByRatingAsc(@Param("productId") Long productId);

    @Query("SELECT COUNT(r) FROM Review r WHERE r.orderOption = :orderProduct")
    Long countReviewsByOrderProduct(@Param("orderProduct") OrderOption orderOption);


}
