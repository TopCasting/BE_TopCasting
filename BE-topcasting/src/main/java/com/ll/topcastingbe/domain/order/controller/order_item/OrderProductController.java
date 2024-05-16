package com.ll.topcastingbe.domain.order.controller.order_item;

import com.ll.topcastingbe.domain.member.entity.Member;
import com.ll.topcastingbe.domain.member.repository.MemberRepository;
import com.ll.topcastingbe.domain.member.service.MemberService;
import com.ll.topcastingbe.domain.order.dto.order_item.FindOrderProductDto;
import com.ll.topcastingbe.domain.order.dto.order_item.response.FindOrderProductResponse;
import com.ll.topcastingbe.domain.order.service.order_product.OrderProductService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class OrderProductController {
    private final MemberService memberService;
    private final OrderProductService orderProductService;
    private final MemberRepository memberRepository;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/order-product/{orderId}")
    public ResponseEntity<List<FindOrderProductDto>> orderProductFindAll(@PathVariable("orderId") final UUID orderId,
                                                                         @AuthenticationPrincipal final UserDetails userDetails) {

        final Member member = memberRepository.findById(1L).get();
        final List<FindOrderProductResponse> findOrderProductResponse = orderProductService.findAllByOrderId(orderId, member);
        final List<FindOrderProductDto> findOrderProductDto = FindOrderProductDto.ofList(findOrderProductResponse);

        return ResponseEntity.ok(findOrderProductDto);
    }
}
