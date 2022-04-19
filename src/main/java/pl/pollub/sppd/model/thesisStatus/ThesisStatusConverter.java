package pl.pollub.sppd.model.thesisStatus;

import javax.persistence.AttributeConverter;
import java.util.stream.Stream;

public class ThesisStatusConverter implements AttributeConverter<ThesisStatus, Long> {

    @Override
    public Long convertToDatabaseColumn(ThesisStatus thesisStatus) {
        if (thesisStatus == null) {
            return null;
        }
        return thesisStatus.getNumber();
    }

    @Override
    public ThesisStatus convertToEntityAttribute(Long dbData) {
        if (dbData == null) {
            return null;
        }
        return Stream.of(ThesisStatus.values())
                .filter(f -> f.getNumber().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);

    }
}