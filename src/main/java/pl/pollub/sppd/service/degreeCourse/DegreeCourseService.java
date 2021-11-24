package pl.pollub.sppd.service.degreeCourse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pollub.sppd.model.faculty.DegreeCourse;
import pl.pollub.sppd.model.faculty.Faculty;
import pl.pollub.sppd.model.repository.DegreeCourseRepository;
import pl.pollub.sppd.model.repository.PersonRepository;
import pl.pollub.sppd.service.degreeCourse.DegreeCourseDto;
import pl.pollub.sppd.service.exceptions.NotFoundException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DegreeCourseService {

    private final DegreeCourseRepository repository;
    private final PersonRepository personRepository;

    public List<DegreeCourseDto> get(String login) {
        Set<DegreeCourse> degreeCourseSet = personRepository.findPersonByLogin(login).getFaculty().getDegreeCourses();
        return degreeCourseSet.stream()
                .map(DegreeCourseDto::degreeCourseToDegreeCourseDto)
                .collect(Collectors.toList());
    }

    public DegreeCourseSaveDto add(DegreeCourseSaveDto degreeCourse, String login) {
        Faculty faculty =  personRepository.findPersonByLogin(login).getFaculty();
        DegreeCourse course = DegreeCourseSaveDto.degreeCourseSaveDtoToDegreeCourse(degreeCourse);
        course.setFaculty(faculty);
        repository.save(course);
        return degreeCourse;
    }

    public DegreeCourseSaveDto update(DegreeCourseDto degreeCourse, String login) throws NotFoundException {
        DegreeCourse course = repository.findById(degreeCourse.getId())
                .orElseThrow( () -> new  NotFoundException("degree course id, not found!"));
        DegreeCourseDto.degreeCourseDtoToDegreeCourse(degreeCourse, course);
        repository.save(course);
        return degreeCourse;
    }
}
