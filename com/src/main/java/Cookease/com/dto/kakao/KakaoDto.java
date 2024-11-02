package Cookease.com.dto.kakao;

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
    public static class KakaoProfile {
        private Long id;
        private KakaoAccount kakao_account;

        @Getter
        public class KakaoAccount {
            private Profile profile;

            @Getter
            public class Profile {
                private String nickname;
                private String profile_image_url;
            }
        }
    }
}
