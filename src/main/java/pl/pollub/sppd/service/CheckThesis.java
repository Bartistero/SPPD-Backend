package pl.pollub.sppd.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.pollub.sppd.model.Person;
import pl.pollub.sppd.model.ThesisTitle.ThesisTitle;
import pl.pollub.sppd.model.permission.Permission;
import pl.pollub.sppd.model.repository.PersonRepository;
import pl.pollub.sppd.model.repository.YearRepository;
import pl.pollub.sppd.model.thesisStatus.ThesisStatus;
import pl.pollub.sppd.service.Thesis.ThesisDto;
import pl.pollub.sppd.service.Thesis.ThesisSaveDto;
import pl.pollub.sppd.service.exceptions.GeneralException;
import pl.pollub.sppd.service.exceptions.PermissionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CheckThesis {

    private final PersonRepository personRepository;
    private final YearRepository yearRepository;

    public void validateNewThesis(ThesisSaveDto thesis) throws GeneralException {
        List<String> errors = new ArrayList<>();

        if (thesis.getThesisName() == null) {
            errors.add("Thesis name can not be null!");
        }
        if (thesis.getTypeOfThesis() == null) {
            errors.add("Type of thesis can not be null!");
        }
        if (thesis.getYear() == null) {
            errors.add("Years filed can not be null");
        } else if (checkYear(thesis.getYear().getId())) {
            errors.add("Not found given year!");
        }
        if (!checkLecturer(thesis.getLecturer().getId())) {
            errors.add("Lecturer don't exists");
        }
        if (thesis.getAmountPeople() < 1) {
            errors.add("field amountPeople should include minimum 1!");
        }

        if (errors.size() > 0)
            throw new GeneralException(errors);
    }

    public boolean checkStatusThesis(ThesisStatus thesisStatus, Permission permission) {
        switch (permission) {
            case STUDENT:
                return (thesisStatus.equals(ThesisStatus.ADDED_STUDENT) || thesisStatus.equals(ThesisStatus.RESERVED_STUDENT));
            case LECTURER:
                return (thesisStatus.equals(ThesisStatus.ACCEPTED_LECTURER) || thesisStatus.equals(ThesisStatus.ADDED_LECTURER));
            case ADMIN:
                return (thesisStatus.equals(ThesisStatus.ACCEPTED_FACULTY) || thesisStatus.equals(ThesisStatus.ARCHIVED));
            default:
                return false;
        }
    }

    public void checkUpdateThesis(ThesisSaveDto thesisDto, Person person, ThesisTitle thesisTitle) throws GeneralException {

        List<String> errors = new ArrayList<>();

        if (!checkStatusThesis(thesisDto.getThesisStatus(), person.getPermission())) {
            errors.add("User can not make this action!");
        }
        if (!thesisTitle.getFaculty().equals(person.getFaculty())) {
            errors.add("This thesis don't belongs to user;s faculty!");
        }
        thesisTitle.getListOfPersonThesis().stream()
                .filter(f -> {
                    if (f.getId().equals(person.getId()))
                        errors.add("the user has been taken down to this thesis");
                    return true;
                });
        if (errors.size() > 0)
            throw new GeneralException(errors);
    }

    private boolean checkYear(Long id) {
        return yearRepository.findById(id).isEmpty();
    }

    private boolean checkLecturer(Long id) {
        Optional<Person> person = personRepository.findById(id);
        return person.isPresent() && person.get().getPermission().equals(Permission.LECTURER);
    }
}
