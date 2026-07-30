package com.gabriel.juno.infraestructure.out.config.empleado;

import com.gabriel.juno.domain.models.empleados.utils.Estado;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EstadoConverter implements AttributeConverter<Estado, String> {

    @Override
    public Estado convertToEntityAttribute(String s) {
        return Estado.valueOf(s.trim().toUpperCase());
    }

    @Override
    public String convertToDatabaseColumn(Estado estado) {
        return estado.name().toLowerCase();
    }
}
