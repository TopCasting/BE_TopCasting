package com.ll.topcastingbe.domain.member.service;

import com.ll.topcastingbe.domain.address.repository.AddressRepository;
import com.ll.topcastingbe.domain.address.service.AddressService;
import com.ll.topcastingbe.domain.cart.entity.Cart;
import com.ll.topcastingbe.domain.cart.repository.CartOptionRepository;
import com.ll.topcastingbe.domain.cart.repository.CartRepository;
import com.ll.topcastingbe.domain.member.dto.AdditionalInfoRequestDto;
import com.ll.topcastingbe.domain.member.dto.JoinRequestDto;
import com.ll.topcastingbe.domain.member.dto.MemberInfoResponseDto;
import com.ll.topcastingbe.domain.address.entity.Address;
import com.ll.topcastingbe.domain.member.entity.Member;
import com.ll.topcastingbe.domain.member.exception.NicknameExistsException;
import com.ll.topcastingbe.domain.member.exception.PasswordNotMatchException;
import com.ll.topcastingbe.domain.member.exception.UsernameExistsException;
import com.ll.topcastingbe.domain.member.exception.UserNotFoundException;
import com.ll.topcastingbe.domain.member.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
<<<<<<< Updated upstream
    private final CartOptionRepository cartOptionRepository;
=======
    private final CartItemRepository cartItemRepository;
    private final AddressService addressService;
    private final AddressRepository addressRepository;
>>>>>>> Stashed changes

    @Transactional
    public ResponseEntity<String> join(JoinRequestDto joinRequestDto) {

        if (checkUsername(joinRequestDto.getUsername())) {
            throw new UsernameExistsException();
        }
        
        if (!joinRequestDto.getPassword().equals(joinRequestDto.getPasswordCheck())) {
            throw new PasswordNotMatchException();
        }
        if (checkNickname(joinRequestDto.getNickname())) {
            throw new NicknameExistsException();
        }
        
        Member member = Member.builder()
<<<<<<< Updated upstream
                .username(joinRequestDto.getUsername())
                .password(passwordEncoder.encode(joinRequestDto.getPassword()))
                .nickname(joinRequestDto.getNickname())
                .name(joinRequestDto.getName())
                .email(joinRequestDto.getEmail())
                .birthDate(joinRequestDto.getBirthDate())
                .phoneNumber(joinRequestDto.getPhoneNumber())
                .address(Address.builder()
                        .address1(joinRequestDto.getAddress1())
                        .address2(joinRequestDto.getAddress2())
                        .zipcode(joinRequestDto.getZipcode())
                        .build())
                .build();
=======
                                .username(joinRequestDto.getUsername())
                                .password(passwordEncoder.encode(joinRequestDto.getPassword()))
                                .nickname(joinRequestDto.getNickname())
                                .name(joinRequestDto.getName())
                                .email(joinRequestDto.getEmail())
                                .birthDate(joinRequestDto.getBirthDate())
                                .phoneNumber(joinRequestDto.getPhoneNumber())
                                .build();
>>>>>>> Stashed changes
        member.grantRole();
        memberRepository.save(member);

        Address address = Address.builder()
                                  .member(member)
                                  .primaryAddress(joinRequestDto.getPrimaryAddress())
                                  .secondaryAddress(joinRequestDto.getSecondaryAddress())
                                  .zipcode(joinRequestDto.getZipcode())
                                  .build();

        addressRepository.save(address);

        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

    public boolean checkUsername(String username) {
        return memberRepository.findByUsername(username) != null;
    }

    public boolean checkNickname(String nickname) {
        return memberRepository.findByNickname(nickname) != null;
    }

    public Member findMember(String username) {
        return memberRepository.findByUsername(username);
    }

    public MemberInfoResponseDto findMemberInfo(String username) {
        return new MemberInfoResponseDto(memberRepository.findByUsername(username));
    }

    @Transactional
    public void modifyMember(Long memberId, String nickname, String password, String email, String phoneNumber) {
        //패스워드가 실제 member 패스워드와 일치하는지 확인
        Member findMember = validateAndFindMember(memberId, password);
<<<<<<< Updated upstream

        Address address = Address.builder()
                .address1(address1)
                .address2(address2)
                .zipcode(zipcode).build();

        findMember.changeDetails(nickname, email, address, phoneNumber);
=======
        findMember.changeDetails(nickname, email, phoneNumber);
>>>>>>> Stashed changes
    }

    private Member validateAndFindMember(Long memberId, String password) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new UserNotFoundException());

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new PasswordNotMatchException();
        }
        return member;
    }

    @Transactional
    public void removeMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new UserNotFoundException());
        //장바구니가 있다면 장바구니 먼저 삭제
        Cart cart = cartRepository.findCartByMemberId(member.getId()).orElse(null);
        if (cart != null) {
            cartOptionRepository.deleteCartItemByCartId(cart.getId());
            cartRepository.delete(cart);
        }

        Address findAddress = addressRepository.findByMember(member);
        if (findAddress != null) {
            addressRepository.delete(findAddress);
        }
        memberRepository.delete(member);
    }

    @Transactional
    public void saveAdditionalInfo(String name, AdditionalInfoRequestDto requestDto) {
        Member findMember = memberRepository.findByUsername(name);
        if (findMember == null) {
            return;
        }
        Address address = Address.builder()
<<<<<<< Updated upstream
                .address1(requestDto.getAddress1())
                .address2(requestDto.getAddress2())
                .zipcode(requestDto.getZipcode())
                .build();
        findMember.changeDetailsForSicailLogin(requestDto.getNickname(), address, requestDto.getPhoneNumber(),
=======
                                  .primaryAddress(requestDto.getAddress1())
                                  .secondaryAddress(requestDto.getAddress2())
                                  .zipcode(requestDto.getZipcode())
                                  .build();
        findMember.changeDetailsForSicailLogin(requestDto.getNickname(),requestDto.getPhoneNumber(),
>>>>>>> Stashed changes
                requestDto.getBirthDate());
        addressRepository.save(address);
        findMember.grantRole();
    }

    public boolean verifyUsername(String username) {
        return memberRepository.existsByUsername(username);
    }

    public boolean verifyNickname(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }
}
