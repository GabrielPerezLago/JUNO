package com.gabriel.juno.infraestructure.out.config.auth;

import com.gabriel.juno.domain.models.auth.utils.Tipo;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoAuthConverter implements AttributeConverter<Tipo, String> {

    @Override
    public Tipo convertToEntityAttribute(String s) {
        return Tipo.valueOf(s.trim().toUpperCase());
    }

    @Override
    public String convertToDatabaseColumn(Tipo tipo) {
        return  tipo.name().toLowerCase();
    }
}
