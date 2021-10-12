package pl.pollub.sppd.model.accountStatus;

import javax.persistence.AttributeConverter;
import java.util.stream.Stream;

public class AccountStatusConverter implements AttributeConverter<AccountStatus, Long> {

    @Override
    public Long convertToDatabaseColumn(AccountStatus attribute) {
       if(attribute == null){
           return null;
       }
       return attribute.getNumber();
    }

    @Override
    public AccountStatus convertToEntityAttribute(Long dbData){
       if(dbData == null){
           return null;
       }
       return Stream.of(AccountStatus.values())
               .filter(f -> f.getNumber().equals(dbData))
               .findFirst()
               .orElseThrow(IllegalArgumentException::new);
    }
}
