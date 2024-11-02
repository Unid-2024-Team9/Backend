package Cookease.com.service;

import Cookease.com.config.jwt.JwtProvider;
import Cookease.com.domain.Member;
import Cookease.com.dto.response.JoinResponseDto;
import Cookease.com.repository.MemberJpaRepository;
import Cookease.com.util.KakaoUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static Cookease.com.dto.kakao.KakaoDto.KakaoProfile;
import static Cookease.com.dto.kakao.KakaoDto.OAuthToken;

@Service
@RequiredArgsConstructor
public class OAuthService {
    private final KakaoUtil kakaoUtil;
    private final MemberJpaRepository memberJpaRepository;
    private final JwtProvider jwtProvider;

    public JoinResponseDto oAuthLogin(String accessCode, HttpServletResponse httpServletResponse) {
        OAuthToken oAuthToken = kakaoUtil.requestToken(accessCode);
        KakaoProfile kakaoProfile = kakaoUtil.requestProfile(oAuthToken);

        Long kakaoId = kakaoProfile.getId();

        Member member = memberJpaRepository.findByKakaoId(kakaoId);
//                .orElseGet(() -> createNewMember(kakaoProfile));

        String token = jwtProvider.createToken(member.getKakaoId());
        httpServletResponse.setHeader("Authorization", token);

        return JoinResponseDto.builder()
                .id(member.getId())
                .token(token)
                .build();
    }
}
