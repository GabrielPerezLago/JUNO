package com.gabriel.juno.domain.models.token;

import com.gabriel.juno.domain.utils.modeluitls.BuilderModelBase;

public record Token(
        Long id,
        String token,
        Boolean revoked,
        Boolean expired,
        Long idUsuario
) {
    public static class builder implements BuilderModelBase<Token> {

        private Long id;
        private String token;
        private Boolean revoked;
        private Boolean expired;
        private Long idUsuario;

        public builder(){}


        public builder id(Long id) {
            this.id = id;
            return this;
        }

        public builder token(String token) {
            this.token = token;
            return this;
        }

        public builder revoked(Boolean revoked) {
            this.revoked = revoked;
            return this;
        }

        public builder expired(Boolean expired) {
            this.expired = expired;
            return this;
        }

        public builder idUsuario(Long idUsuario) {
            this.idUsuario = idUsuario;
            return this;
        }

        @Override
        public Token build() {
            return new Token(id, token, revoked, expired, idUsuario);
        }
    }
}
