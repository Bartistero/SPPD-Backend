package pl.pollub.sppd.service.user;

import lombok.Getter;
import lombok.Setter;
import pl.pollub.sppd.model.Person;

@Getter
@Setter
public class LecturerDto {

    Long id;
    String name;
    String surname;

    public static LecturerDto LecturerDtoToUserDto(Person person) {
        LecturerDto lecturerDto = new LecturerDto();
        lecturerDto.setId(person.getId());
        lecturerDto.setName(person.getName());
        lecturerDto.setSurname(person.getSurname());
        return lecturerDto;
    }
}
