package pl.pollub.sppd.service.user;

import lombok.Getter;
import lombok.Setter;
import pl.pollub.sppd.model.Person;
import pl.pollub.sppd.model.faculty.Faculty;
import pl.pollub.sppd.service.PersonAbstractDto;

@Getter
@Setter
public class UserSaveDto extends PersonAbstractDto {

    private String activeToken;
    private String albumNumber;

    public static Person userSaveDtoToPerson(UserSaveDto userSaveDto, Faculty faculty) {
        Person person = adminAbstractDtoToPerson(userSaveDto);
        person.setActivateToken(userSaveDto.getActiveToken());
        person.setAlbumNumber(userSaveDto.getAlbumNumber());
        person.setFaculty(faculty);
        return person;
    }
}
