package pl.pollub.sppd.service.degreeCourse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import pl.pollub.sppd.model.faculty.DegreeCourse;

@Getter
@Setter
@NoArgsConstructor
public class DegreeCourseDto extends DegreeCourseSaveDto {

    private Long id;

    public static DegreeCourseDto degreeCourseToDegreeCourseDto(DegreeCourse degreeCourse) {
        DegreeCourseDto degreeCourseDto = new DegreeCourseDto();
        BeanUtils.copyProperties(degreeCourse, degreeCourseDto);
        return degreeCourseDto;
    }

    public static DegreeCourse degreeCourseDtoToDegreeCourse(DegreeCourseDto degreeCourseDto, DegreeCourse course) {
        BeanUtils.copyProperties(degreeCourseDto, course);
        return course;
    }
}
