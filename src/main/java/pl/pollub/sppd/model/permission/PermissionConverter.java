package pl.pollub.sppd.model.permission;

import javax.persistence.AttributeConverter;
import java.security.Permissions;
import java.util.stream.Stream;

public class PermissionConverter implements AttributeConverter<Permission, Long> {

    @Override
    public Long convertToDatabaseColumn(Permission attribute) {
        if(attribute == null){
            return null;
        }
        return attribute.getNumber();
    }

    @Override
    public Permission convertToEntityAttribute(Long dbData) {
        if(dbData == null){
            return null;
        }
        return Stream.of(Permission.values())
                .filter(f -> f.getNumber().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
