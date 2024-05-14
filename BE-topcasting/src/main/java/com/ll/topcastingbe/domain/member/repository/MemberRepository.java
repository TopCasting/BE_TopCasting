package com.ll.topcastingbe.domain.member.repository;

import com.ll.topcastingbe.domain.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByNickname(String nickname);

    Member findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByNickname(String nickname);
}
