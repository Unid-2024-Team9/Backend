package Cookease.com.dto.kakao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Properties;

public class KakaoDto {

    @Getter
    public static class OAuthToken {
        private String access_token;
        private String token_type;
        private String refresh_token;
        private int expires_in;
        private String scope;
        private int refresh_token_expires_in;
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class KakaoProfile {
        private Long id;
        @JsonProperty("kakao_account")
        private KakaoAccount kakao_account;

        @Getter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public class KakaoAccount {
            @JsonProperty("pofile")
            private Profile profile;

            @Getter
            @JsonIgnoreProperties(ignoreUnknown = true)
            public class Profile {
                private String nickname;
                private String profile_image_url;
            }
        }
    }
}
