package com.ll.topcastingbe.domain.member.entity;

import com.ll.topcastingbe.domain.address.entity.Address;
import com.ll.topcastingbe.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Builder
@Getter
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;


    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

    private LocalDate birthDate;

    private String roles;

    public List<String> getRoleList() {
        if (this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public void grantRole() {
        if (username.equals("admin")) {
            roles = "ROLE_ADMIN, ROLE_USER";
        } else {
            roles = "ROLE_USER";
        }
    }

    public void changeDetails(String nickname, String email,String phoneNumber) {
        this.nickname = nickname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void changeDetailsForSicailLogin(String nickname,String phoneNumber, LocalDate birthDate) {
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }


    public boolean checkMemberIdMatch(Long memberId) {
        return id.equals(memberId);
    }
}
