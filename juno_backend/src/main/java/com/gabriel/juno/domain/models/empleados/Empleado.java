package com.gabriel.juno.domain.models.empleados;

import ch.qos.logback.core.joran.action.BaseModelAction;
import com.gabriel.juno.domain.models.centro.Centro;
import com.gabriel.juno.domain.models.empleados.utils.Estado;
import com.gabriel.juno.domain.models.empleados.utils.Rol;
import com.gabriel.juno.domain.models.usuario.Usuario;
import com.gabriel.juno.domain.utils.modeluitls.BuilderModelBase;

public record Empleado(
        Long id_usuario,
        Rol rol,
        Estado estad,
        Long id_centro,
        Long id_auala
) {

    public Builder builder() {
        return new Builder();
    }

    private class Builder implements BuilderModelBase<Empleado> {
        private Long id_usuario;
        private Rol rol;
        private Estado estad;
        private Long id_centro;
        private Long id_auala;

        @Override
        public Empleado build() {
            return new Empleado(
                    id_usuario,
                    rol,
                    estad,
                    id_centro,
                    id_auala
            );
        }
    }

}
