package com.ll.topcastingbe.domain.address.entity;

import com.ll.topcastingbe.domain.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String zipcode;


    @Column(nullable = false)
    private String primaryAddress;
    private String secondaryAddress;

    public void changeAddress(String primaryAddress, String secondaryAddress, String zipcode) {
        this.primaryAddress = primaryAddress;
        this.secondaryAddress = secondaryAddress;
        this.zipcode = zipcode;
    }

}
