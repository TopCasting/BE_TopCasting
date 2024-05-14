package com.ll.topcastingbe.domain.address.repository;

import com.ll.topcastingbe.domain.address.entity.Address;
import com.ll.topcastingbe.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findByMember(Member member);
}
