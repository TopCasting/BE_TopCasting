package com.ll.topcastingbe.domain.order.controller.order_product;

import com.ll.topcastingbe.domain.member.entity.Member;
import com.ll.topcastingbe.domain.member.repository.MemberRepository;
import com.ll.topcastingbe.domain.member.service.MemberService;
import com.ll.topcastingbe.domain.order.dto.order_item.response.FindOrderProductResponseDto;
import com.ll.topcastingbe.domain.order.service.order_product.OrderProductService;
import com.ll.topcastingbe.global.security.auth.PrincipalDetails;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<List<FindOrderProductResponseDto>> orderProductFindAll(
            @PathVariable("orderId") final UUID orderId,
            @AuthenticationPrincipal PrincipalDetails principal) {
        Member member = principal.getMember();
        List<FindOrderProductResponseDto> findOrderProductResponseDto
                = orderProductService.findAllByOrderId(orderId, member);

        return ResponseEntity.ok(findOrderProductResponseDto);
    }
}
