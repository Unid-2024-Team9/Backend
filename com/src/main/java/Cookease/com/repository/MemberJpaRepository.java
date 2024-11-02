package Cookease.com.repository;

import Cookease.com.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
    public Member findByKakaoId(Long kakaoId);
}
