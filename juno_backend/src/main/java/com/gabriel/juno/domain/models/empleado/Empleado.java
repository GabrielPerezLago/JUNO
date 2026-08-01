package com.gabriel.juno.domain.models.empleado;

import com.gabriel.juno.domain.models.aula.Aula;
import com.gabriel.juno.domain.models.centro.Centro;
import com.gabriel.juno.domain.models.empleado.utils.Estado;
import com.gabriel.juno.domain.models.empleado.utils.Rol;
import com.gabriel.juno.domain.utils.modeluitls.BuilderModelBase;

public record Empleado(
        Long id_usuario,
        Rol rol,
        Estado estad,
        Long centro,
        Long aula
) {
    

    public static class Builder implements BuilderModelBase<Empleado> {
        private Long id_usuario;
        private Rol rol;
        private Estado estado;
        private Long idCentro;
        private Long idAula;

        public Builder id_usuario(Long id_usuario) {
            this.id_usuario = id_usuario;
            return this;
        }

        public Builder rol(Rol rol) {
            this.rol = rol;
            return this;
        }

        public Builder estado(Estado estado) {
            this.estado = estado;
            return this;
        }

        public Builder centro(Long idCentro) {
            this.idCentro = idCentro;
            return this;
        }

        public Builder aula(Long idAula) {
            this.idAula = idAula;
            return this;
        }

        @Override
        public Empleado build() {
            return new Empleado(
                    id_usuario,
                    rol,
                    estado,
                    idCentro,
                    idAula
            );
        }
    }

}
