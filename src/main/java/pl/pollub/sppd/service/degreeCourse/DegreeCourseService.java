package pl.pollub.sppd.service.degreeCourse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pollub.sppd.model.faculty.DegreeCourse;
import pl.pollub.sppd.model.repository.DegreeCourseRepository;
import pl.pollub.sppd.model.repository.PersonRepository;

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
}
