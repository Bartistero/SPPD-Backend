package pl.pollub.sppd.service.degreeCourse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import pl.pollub.sppd.model.faculty.DegreeCourse;

@Getter
@Setter
@NoArgsConstructor
public class DegreeCourseSaveDto{

    private String name;

    public static DegreeCourse degreeCourseSaveDtoToDegreeCourse(DegreeCourseSaveDto degreeCourseDto) {
        DegreeCourse degreeCourse = new DegreeCourse();
        BeanUtils.copyProperties(degreeCourseDto, degreeCourse);
        return degreeCourse;
    }

}
