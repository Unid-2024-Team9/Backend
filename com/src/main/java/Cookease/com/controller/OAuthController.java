package Cookease.com.controller;

import Cookease.com.domain.Member;
import Cookease.com.dto.response.JoinResponseDto;
import Cookease.com.service.OAuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class OAuthController {

    private final OAuthService oAuthService;

    @GetMapping("/login/kakao")
    public ResponseEntity<JoinResponseDto> kakaoLogin(@RequestParam("code") String accessCode, HttpServletResponse httpServletResponse) {
        JoinResponseDto joinResponseDto = oAuthService.oAuthLogin(accessCode, httpServletResponse);

        return ResponseEntity.ok(joinResponseDto);
    }

}
