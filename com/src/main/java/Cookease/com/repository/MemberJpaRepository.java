package Cookease.com.repository;

import Cookease.com.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberJpaRepository extends JpaRepository<Member, Long> {
    public Optional<Member> findByKakaoId(Long kakaoId);
}
