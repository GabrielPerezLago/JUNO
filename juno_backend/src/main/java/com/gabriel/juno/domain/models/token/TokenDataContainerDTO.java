package com.gabriel.juno.domain.models.token;

import com.gabriel.juno.domain.utils.modeluitls.BuilderModelBase;

public record TokenDataContainerDTO(
        String token,
        String refreshToken
) {



    public static class builder implements BuilderModelBase<TokenDataContainerDTO> {

        private String token;
        private String refreshToken;

        public builder refreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public builder token(String token) {
            this.token = token;
            return this;
        }

        @Override
        public TokenDataContainerDTO build() {
            return new TokenDataContainerDTO(token, refreshToken);
        }
    }
}
