package com.ll.topcastingbe.domain.address.service;

import com.ll.topcastingbe.domain.address.entity.Address;
import com.ll.topcastingbe.domain.address.repository.AddressRepository;
import com.ll.topcastingbe.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    public Address findAddress(Member member) {
        return addressRepository.findByMember(member);
    }
}
