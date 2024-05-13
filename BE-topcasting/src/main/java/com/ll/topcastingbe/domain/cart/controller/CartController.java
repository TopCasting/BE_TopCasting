package com.ll.topcastingbe.domain.cart.controller;

import com.ll.topcastingbe.domain.cart.dto.AddToCartRequestDto;
import com.ll.topcastingbe.domain.cart.dto.CartOptionListResponseDto;
import com.ll.topcastingbe.domain.cart.dto.CartProductQuantityUpdateRequestDto;
import com.ll.topcastingbe.domain.cart.service.CartService;
import com.ll.topcastingbe.global.security.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
@Slf4j
public class CartController {

    private final CartService cartService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<?> cartList(@AuthenticationPrincipal PrincipalDetails principal) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.findCartOptionList(principal.getMember().getId()));
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<?> cartOptionAdd(@AuthenticationPrincipal PrincipalDetails principal,
                                           @RequestBody AddToCartRequestDto cartOptionDto) {
        cartService.addCartOption(principal.getMember().getId(), cartOptionDto.getOptionId(),
                cartOptionDto.getProductQuantity());
        CartOptionListResponseDto cartOptionList = cartService.findCartOptionList(principal.getMember().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(cartOptionList);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{cartOptionId}")
    public ResponseEntity<?> cartOptionModify(@AuthenticationPrincipal PrincipalDetails principal,
                                              @PathVariable Long cartOptionId,
                                              @RequestBody CartProductQuantityUpdateRequestDto productQuantityDto) {
        cartService.modifyCartOption(principal.getMember().getId(), cartOptionId,
                productQuantityDto.getProductQuantity());
        CartOptionListResponseDto cartOptionList = cartService.findCartOptionList(principal.getMember().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(cartOptionList);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{cartOptionId}")
    public ResponseEntity<?> cartOptionDelete(@AuthenticationPrincipal PrincipalDetails principal,
                                              @PathVariable Long cartOptionId) {
        cartService.removeCartOption(principal.getMember().getId(), cartOptionId);
        return ResponseEntity.ok(null);
    }

}
