package pl.pollub.sppd.service.degreeCourse;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import pl.pollub.sppd.model.faculty.DegreeCourse;

@Getter
@Setter
public class DegreeCourseDto {

    Long id;
    String name;

    public static DegreeCourseDto degreeCourseToDegreeCourseDto(DegreeCourse degreeCourse) {
        DegreeCourseDto degreeCourseDto = new DegreeCourseDto();
        BeanUtils.copyProperties(degreeCourse, degreeCourseDto);
        return degreeCourseDto;
    }

    public static DegreeCourse degreeCourseDtoToDegreeCourse(DegreeCourseDto degreeCourseDto) {
        DegreeCourse degreeCourse = new DegreeCourse();
        BeanUtils.copyProperties(degreeCourseDto, degreeCourse);
        return degreeCourse;
    }
}