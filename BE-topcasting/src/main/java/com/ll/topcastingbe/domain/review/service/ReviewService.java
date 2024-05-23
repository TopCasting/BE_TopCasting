package com.ll.topcastingbe.domain.review.service;


import com.ll.topcastingbe.domain.member.entity.Member;
import com.ll.topcastingbe.domain.member.repository.MemberRepository;
import com.ll.topcastingbe.domain.order.entity.OrderOption;
import com.ll.topcastingbe.domain.order.entity.Orders;
import com.ll.topcastingbe.domain.order.repository.order.OrderRepository;
import com.ll.topcastingbe.domain.order.repository.order_product.OrderProductRepository;
import com.ll.topcastingbe.domain.review.dto.AddNormalReviewRequestDto;
import com.ll.topcastingbe.domain.review.dto.ModifyReviewRequestDto;
import com.ll.topcastingbe.domain.review.dto.ReviewDetailResponseDto;
import com.ll.topcastingbe.domain.review.dto.ReviewListResponseDto;
import com.ll.topcastingbe.domain.review.entity.Review;
import com.ll.topcastingbe.domain.review.repository.ReviewRepository;
import com.ll.topcastingbe.global.exception.member.UserNotFoundException;
import com.ll.topcastingbe.global.exception.order.OrderNotFoundException;
import com.ll.topcastingbe.global.exception.review.DuplicateReviewException;
import com.ll.topcastingbe.global.exception.review.ReviewNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final MemberRepository memberRepository;


    public ReviewListResponseDto findReviewList() {

        List<Review> all = reviewRepository.findAll();
        return new ReviewListResponseDto(all);
    }


    public ReviewDetailResponseDto findReviewDetail(Long reviewId) {
        Optional<Review> or = reviewRepository.findById(reviewId);
        if (or.isEmpty()) {
            throw new ReviewNotFoundException();
        }

        return new ReviewDetailResponseDto(or.get());
    }


    //Item 이름으로 리뷰 추가
    @Transactional
    public ReviewDetailResponseDto addNormalReview(String productName, Member member, UUID orderId,
                                                   AddNormalReviewRequestDto addNormalReviewRequestDto) {

        Orders orders = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException());
        List<OrderOption> findOrderOptions = orderProductRepository.findByOrder(orders);
        OrderOption findOrderOption = findOrderOptions.stream()
                .filter(orderProduct -> orderProduct.getProductName().equals(productName))
                .findFirst()
                .orElseThrow(
                        () -> new OrderNotFoundException());

        Review review = Review.builder()
                .writer(member)
                .orderOption(findOrderOption)
                .image(null)
                .title(addNormalReviewRequestDto.getTitle())
                .content(addNormalReviewRequestDto.getContent())
                .rating(addNormalReviewRequestDto.getRating())
                .build();

        return new ReviewDetailResponseDto((Review) reviewRepository.save(review));
    }

    @Transactional
    public ReviewDetailResponseDto modifyReview(Long reviewId, ModifyReviewRequestDto modifyReviewRequestDto) {
        Review findReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException());

        if (modifyReviewRequestDto.getImgUrl() != null) {
            //TODO 이미지 url 존재할시
        }
        findReview.modifyTitle(modifyReviewRequestDto.getTitle());
        findReview.modifyContent(modifyReviewRequestDto.getContent());
        findReview.modifyImg(null);
        findReview.modifyRating(modifyReviewRequestDto.getRating());
        return new ReviewDetailResponseDto(findReview);
    }


    @Transactional
    public void deleteReview(Long reviewId) {
        Review findReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException());
        reviewRepository.delete(findReview);
    }

    public ReviewListResponseDto findReviewRating(int rating, Long productId) {
        List<Review> findReviewList = reviewRepository.findByRatingAndProductId(rating, productId);
        return new ReviewListResponseDto(findReviewList);
    }

    public ReviewListResponseDto findProductReviewList(Long productId, String sort) {
        if (sort.equals("DESC")) {
            return new ReviewListResponseDto(reviewRepository.findByProductIdOrderByRatingDesc(productId));
        } else if (sort.equals("ASC")) {
            return new ReviewListResponseDto(reviewRepository.findByProductIdOrderByRatingAsc(productId));
        } else {
            throw new ReviewNotFoundException();
        }
    }

    public ReviewListResponseDto findMemberReviewList(Long memberId) {
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new UserNotFoundException());
        return new ReviewListResponseDto(reviewRepository.findByWriter(findMember));
    }

    public void makeReviewSummary() {

    }

    public void verifyReview(String productName, UUID orderId) {

        Orders findOrders = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException());

        List<OrderOption> findOrderOptions = orderProductRepository.findByOrder(findOrders);
        OrderOption findOrderOption = findOrderOptions.stream()
                .filter(orderProduct -> orderProduct.getProductName().equals(productName))
                .findFirst()
                .orElseThrow(
                        () -> new OrderNotFoundException());

        Long reviewCount = reviewRepository.countReviewsByOrderProduct(findOrderOption);

        if (reviewCount >= 1) {
            throw new DuplicateReviewException();
        }
    }
}
