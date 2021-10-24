package pl.pollub.sppd.service.faculty;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pollub.sppd.model.faculty.Faculty;
import pl.pollub.sppd.model.permission.Permission;
import pl.pollub.sppd.model.repository.FacultyRepository;
import pl.pollub.sppd.service.CheckPermission;
import pl.pollub.sppd.service.exceptions.AlreadyExistsException;
import pl.pollub.sppd.service.exceptions.GeneralException;
import pl.pollub.sppd.service.exceptions.NotFoundException;
import pl.pollub.sppd.service.exceptions.PermissionException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public List<FacultyDto> get() throws PermissionException {
        List<Faculty> facultyList = facultyRepository.findAll();
        return facultyList.stream()
                .map(FacultyDto::facultyToFacultyDto)
                .collect(Collectors.toList());
    }

    public FacultySaveDto post(FacultySaveDto facultySaveDto) throws AlreadyExistsException {
        if(facultyRepository.findFacultyByName(facultySaveDto.getName()).isPresent())
               throw new AlreadyExistsException("Faculty with name: " + facultySaveDto.getName() + " already exists");
        facultyRepository.save(FacultySaveDto.facultySaveDtoToFaculty(facultySaveDto));
        return facultySaveDto;
    }

    public FacultyDto update(FacultyDto facultyDto) throws AlreadyExistsException, NotFoundException, GeneralException {
        if(!facultyRepository.findFacultyByName(facultyDto.getName()).isEmpty())
            throw new AlreadyExistsException("Faculty with name: " + facultyDto.getName() + " already exists");
        if(facultyDto.getId() == null)
            throw new GeneralException("Field id can not be null!");
        facultyRepository.findById(facultyDto.getId())
                .orElseThrow( ()-> new NotFoundException("Faculty with id: " +facultyDto.getId() + " don't exists"));
        facultyRepository.save(FacultyDto.facultyDtoToFaculty(facultyDto));
        return facultyDto;
    }

    public FacultyDto delete(FacultyDto facultyDto) throws NotFoundException, GeneralException {
        if(facultyDto.getId() == null)
            throw new GeneralException("Field id can not be null!");
        facultyRepository.findById(facultyDto.getId())
                .orElseThrow( ()-> new NotFoundException("Faculty with id: " +facultyDto.getId() + " don't exists"));
        facultyRepository.delete(FacultyDto.facultyDtoToFaculty(facultyDto));
        return facultyDto;
    }
}
