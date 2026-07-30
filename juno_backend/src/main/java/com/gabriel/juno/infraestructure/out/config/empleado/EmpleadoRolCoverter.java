package com.gabriel.juno.infraestructure.out.config.empleado;

import com.gabriel.juno.domain.models.empleados.utils.Rol;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


/**
 * @apiNote @Converter
 * Metodo que conviente automaticamente el enum Rol a minusculas en direccion a la api , y en mayusculas en direccion a la entidad.
 */
@Converter(autoApply = true)
public class EmpleadoRolCoverter implements AttributeConverter<Rol, String> {

    @Override
    public String convertToDatabaseColumn(Rol rol) {
        if (rol == null) return null;
        return rol.name().toLowerCase();
    }

    @Override
    public Rol convertToEntityAttribute(String s) {
        if (s == null) return null;
        return Rol.valueOf(s.trim().toUpperCase());
    }
}
