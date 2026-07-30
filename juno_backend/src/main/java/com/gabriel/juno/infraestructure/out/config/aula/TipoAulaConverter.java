package com.gabriel.juno.infraestructure.out.config.aula;

import com.gabriel.juno.domain.models.aula.utils.TipoAula;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoAulaConverter implements AttributeConverter<TipoAula, String> {


    @Override
    public TipoAula convertToEntityAttribute(String s) {
        return TipoAula.valueOf(s.trim().toUpperCase());
    }

    @Override
    public String convertToDatabaseColumn(TipoAula tipoAula) {
        return  tipoAula.name().toLowerCase();
    }
}
