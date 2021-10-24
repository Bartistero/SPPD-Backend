package pl.pollub.sppd.model.sex;

import pl.pollub.sppd.model.accountStatus.AccountStatus;

import javax.persistence.AttributeConverter;
import java.util.stream.Stream;

public class SexConverter implements AttributeConverter<Sex, Long> {

    @Override
    public Long convertToDatabaseColumn(Sex sex) {
        if (sex == null) {
            return null;
        }
        return sex.getNumber();
    }

    @Override
    public Sex convertToEntityAttribute(Long dbData) {
        if (dbData == null) {
            return null;
        }
        return Stream.of(Sex.values())
                .filter(f -> f.getNumber().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}