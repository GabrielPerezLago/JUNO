package com.gabriel.juno.domain.models.aula;

import com.gabriel.juno.domain.models.aula.utils.TipoAula;
import com.gabriel.juno.domain.utils.modeluitls.BuilderModelBase;

public record Aula(
        Long id,
        String nombre,
        TipoAula tipo,
        Long idCentro
) {
    private class Builder implements BuilderModelBase<Aula> {
        private Long id;
        private String nombre;
        private TipoAula tipo;
        private Long idCentro;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder tipo(TipoAula tipo) {
            this.tipo = tipo;
            return this;
        }

        public Builder idCentro(Long idCentro) {
            this.idCentro = idCentro;
            return this;
        }

        @Override
        public Aula build() {
            return new Aula(
                    this.id,
                    this.nombre,
                    this.tipo,
                    this.idCentro
            );
        }
    }
}
