package pl.pollub.sppd.service.faculty;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.pollub.sppd.model.repository.DegreeCourseRepository;
import pl.pollub.sppd.model.repository.FacultyRepository;
import pl.pollub.sppd.service.exceptions.GeneralException;
import pl.pollub.sppd.service.exceptions.NotFoundException;

@Component
@RequiredArgsConstructor
public class FacultyVerification {

    private final FacultyRepository facultyRepository;
    private final DegreeCourseRepository degreeCourseRepository;

    public void checkAvailabilityFaculty(Long id) throws NotFoundException, GeneralException {
        if(id == null)
            throw new GeneralException("Field id can not be null!");
        facultyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Faculty with id: " +
                       id + " not found!"));
    }

    public void checkDegreeCourse(Long id) throws NotFoundException, GeneralException {
        if(id == null)
            throw new GeneralException("Field id can not be null!");
        degreeCourseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Degree Course  with id: " +
                        id + " not found!"));
    }
}
