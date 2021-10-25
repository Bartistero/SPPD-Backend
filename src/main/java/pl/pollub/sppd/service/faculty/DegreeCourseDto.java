package pl.pollub.sppd.service.faculty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DegreeCourseDto {

    private Long id;
    private String name;
    private FacultyDto facultyDto;
}
