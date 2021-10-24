package pl.pollub.sppd.service.faculty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import pl.pollub.sppd.model.faculty.Faculty;

@Getter
@Setter
@NoArgsConstructor
public class FacultyDto {

    private Long id;
    private String name;

    public static Faculty facultyDtoToFaculty(FacultyDto facultyDto) {
        Faculty faculty = new Faculty();
        BeanUtils.copyProperties(facultyDto,faculty);
        return faculty;
    }

    public static FacultyDto facultyToFacultyDto(Faculty faculty) {
        FacultyDto facultyDto = new FacultyDto();
        BeanUtils.copyProperties(faculty, facultyDto);
        return facultyDto;
    }
}
