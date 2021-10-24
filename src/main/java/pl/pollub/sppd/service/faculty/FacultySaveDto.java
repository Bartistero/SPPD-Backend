package pl.pollub.sppd.service.faculty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pollub.sppd.model.faculty.Faculty;

@Getter
@Setter
@NoArgsConstructor
public class FacultySaveDto {

    private String name;

    public static Faculty facultySaveDtoToFaculty(FacultySaveDto facultySaveDto) {
        Faculty faculty = new Faculty();
        faculty.setName(facultySaveDto.getName());
        return faculty;
    }
}
