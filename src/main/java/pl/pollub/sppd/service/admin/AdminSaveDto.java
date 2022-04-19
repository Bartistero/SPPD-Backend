package pl.pollub.sppd.service.admin;

import lombok.Getter;
import lombok.Setter;
import pl.pollub.sppd.model.Person;
import pl.pollub.sppd.service.PersonAbstractDto;
import pl.pollub.sppd.service.faculty.FacultyDto;

@Getter
@Setter
public class AdminSaveDto extends PersonAbstractDto {

    private String activeToken;
    private FacultyDto facultyDto;

    public static Person adminDtoToPerson(AdminSaveDto adminSaveDto) {
        Person person = adminAbstractDtoToPerson(adminSaveDto);
        person.setActivateToken(adminSaveDto.getActiveToken());
        person.setFaculty(FacultyDto.facultyDtoToFaculty(adminSaveDto.getFacultyDto()));
        return person;
    }
}
