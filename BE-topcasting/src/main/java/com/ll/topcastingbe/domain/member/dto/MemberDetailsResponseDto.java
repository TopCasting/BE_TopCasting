package com.ll.topcastingbe.domain.member.dto;

import com.ll.topcastingbe.domain.address.entity.Address;
import com.ll.topcastingbe.domain.member.entity.Member;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberDetailsResponseDto {
    private String username;
    private String nickname;
    private String name;
    private String email;
    private LocalDate birthDate;
    private String phoneNumber;
    private String primaryAddress;
    private String secondaryAddress;
    private String zipcode;

    public static MemberDetailsResponseDto toDto(Member member, Address address) {
        return MemberDetailsResponseDto.builder()
                .username(member.getUsername())
                .nickname(member.getNickname())
                .name(member.getName())
                .email(member.getEmail())
                .birthDate(member.getBirthDate())
                .phoneNumber(member.getPhoneNumber())
                .primaryAddress(address.getPrimaryAddress())
                .secondaryAddress(address.getSecondaryAddress())
                .zipcode(address.getZipcode())
                .build();
    }
}
