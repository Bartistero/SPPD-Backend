package pl.pollub.sppd.model.typeOfThesis;

import javax.persistence.AttributeConverter;
import java.util.stream.Stream;

public class TypeOfThesisConverter implements AttributeConverter<TypeOfThesis, Long> {

    @Override
    public Long convertToDatabaseColumn(TypeOfThesis typeOfThesis) {
        if (typeOfThesis == null) {
            return null;
        }
        return typeOfThesis.getNumber();
    }

    @Override
    public TypeOfThesis convertToEntityAttribute(Long dbData) {
        if (dbData == null) {
            return null;
        }
        return Stream.of(TypeOfThesis.values())
                .filter(f -> f.getNumber().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}