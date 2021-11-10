package pl.pollub.sppd.service.user;

import lombok.Getter;
import lombok.Setter;
import pl.pollub.sppd.model.Person;
import pl.pollub.sppd.service.PersonAbstractDto;

@Getter
@Setter
public class UserSaveDto extends PersonAbstractDto {

    private String activeToken;

    public static Person userSaveDtoToPerson(UserSaveDto userSaveDto) {
        Person person = adminAbstractDtoToPerson(userSaveDto);
        person.setActivateToken(userSaveDto.getActiveToken());
        return person;
    }
}
