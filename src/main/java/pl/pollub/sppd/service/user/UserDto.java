package pl.pollub.sppd.service.user;

import lombok.Getter;
import lombok.Setter;
import pl.pollub.sppd.model.Person;
import pl.pollub.sppd.service.PersonAbstractDto;

@Getter
@Setter
public class UserDto extends PersonAbstractDto {

    String albumNumber;
    Long id;

    public static Person userDtoToPerson(UserDto userDto) {
        Person person = new Person();
        person.setId(userDto.getId());
        return person;
    }

    public static UserDto personToUserDto(Person person) {
        UserDto userDto = (UserDto) personToAdminAbstractDto(person, new UserDto());
        userDto.setId(person.getId());
        userDto.setAlbumNumber(person.getAlbumNumber());
        return userDto;
    }
}
