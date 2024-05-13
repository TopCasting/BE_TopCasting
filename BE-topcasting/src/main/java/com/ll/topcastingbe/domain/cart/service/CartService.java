package com.ll.topcastingbe.domain.cart.service;

import com.ll.topcastingbe.domain.cart.dto.CartOptionListResponseDto;
import com.ll.topcastingbe.domain.cart.entity.Cart;
import com.ll.topcastingbe.domain.cart.entity.CartOption;
import com.ll.topcastingbe.domain.cart.exception.CartOptionNotExistException;
import com.ll.topcastingbe.domain.cart.repository.CartOptionRepository;
import com.ll.topcastingbe.domain.cart.repository.CartRepository;
import com.ll.topcastingbe.domain.member.entity.Member;
import com.ll.topcastingbe.domain.member.exception.UserAndWriterNotMatchException;
import com.ll.topcastingbe.domain.member.exception.UserNotFoundException;
import com.ll.topcastingbe.domain.member.repository.MemberRepository;
import com.ll.topcastingbe.domain.option.entity.Option;
import com.ll.topcastingbe.domain.option.exception.OptionNotFoundException;
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
public class CartService {

    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartOptionRepository cartOptionRepository;
    private final OptionRepository optionRepository;

    //상품페이지에서 장바구니에 추가를 선택한 경우
    @Transactional
    public void addCartOption(Long memberId, Long optionId, int productQuantity) {

        //장바구니가 존재하는지 확인 -> 없으면 생성
        Cart cart = cartRepository.findCartByMemberId(memberId).orElseGet(() -> createCart(memberId));

        CartOption cartOption = cartOptionRepository.findByCartIdAndOptionId(cart.getId(), optionId);
        //카트안에 해당 상품이 없었다면 추가
        if (cartOption == null) {
            Option option = optionRepository.findById(optionId)
                    .orElseThrow(OptionNotFoundException::new);
            cartOptionRepository.save(new CartOption(cart, option, productQuantity));
        } else {
            cartOption.changeProductQuantity(productQuantity);
        }
    }

    private Cart createCart(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(UserNotFoundException::new);
        log.info("장바구니를 생성합니다.");
        return cartRepository.save(new Cart(member));
    }

    //장바구니에 있는 상품을 증감하는 경우
    @Transactional
    public void modifyCartOption(Long memberId, Long cartOptionId, int productQuantity) {
        CartOption cartOption = getCartOptionAndValidateMember(memberId, cartOptionId);

        cartOption.changeProductQuantity(productQuantity);
    }

    private CartOption getCartOptionAndValidateMember(Long memberId, Long cartOptionId) {
        CartOption cartOption = cartOptionRepository.findByIdWithMember(cartOptionId)
                .orElseThrow(CartOptionNotExistException::new);

        if (cartOption.isNotCartOwner(memberId)) {
            throw new UserAndWriterNotMatchException();
        }
        return cartOption;
    }

    public CartOptionListResponseDto findCartOptionList(Long memberId) {
        //장바구니가 존재하는지 확인 -> 없으면 생성
        Cart cart = cartRepository.findCartByMemberId(memberId).orElseGet(() -> createCart(memberId));
        List<CartOption> cartOptions = cartOptionRepository.findByCartId(cart.getId());
        return CartOptionListResponseDto.toDto(cartOptions);
    }

    @Transactional
    public void removeCartOption(Long memberId, Long cartOptionId) {
        CartOption cartOption = getCartOptionAndValidateMember(memberId, cartOptionId);

        cartOptionRepository.delete(cartOption);
    }

}

