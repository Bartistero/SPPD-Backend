package pl.pollub.sppd.service.degreeCourse;

import org.springframework.beans.BeanUtils;
import pl.pollub.sppd.model.faculty.DegreeCourse;

public class DegreeCourseDto {

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