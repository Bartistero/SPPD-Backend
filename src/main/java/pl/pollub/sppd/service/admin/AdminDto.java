package pl.pollub.sppd.service.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pollub.sppd.model.Person;
import pl.pollub.sppd.service.PersonAbstractDto;
import pl.pollub.sppd.service.faculty.FacultyDto;

@Getter
@Setter
@NoArgsConstructor
public class AdminDto extends PersonAbstractDto {
        
    private Long id;
    private FacultyDto facultyDto;

    public static Person adminDtoToPerson(AdminDto adminDto){
        Person person = adminAbstractDtoToPerson(adminDto);
        person.setId(adminDto.getId());
        person.setFaculty(FacultyDto.facultyDtoToFaculty(adminDto.getFacultyDto()));
        return person;
    }

    public static AdminDto personToAdminDto(Person person) {
        AdminDto adminDto = (AdminDto) personToAdminAbstractDto(person, new AdminDto());
        adminDto.setId(person.getId());
        adminDto.setFacultyDto(FacultyDto.facultyToFacultyDto(person.getFaculty()));
        return adminDto;
    }
}
