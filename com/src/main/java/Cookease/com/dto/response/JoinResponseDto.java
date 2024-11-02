package Cookease.com.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinResponseDto {
    private Long id;
    private String token;

    @Builder
    public JoinResponseDto(Long id, String token) {
        this.id = id;
        this.token = token;
    }
}
