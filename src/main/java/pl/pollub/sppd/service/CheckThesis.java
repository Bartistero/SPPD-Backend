package pl.pollub.sppd.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.pollub.sppd.model.Person;
import pl.pollub.sppd.model.permission.Permission;
import pl.pollub.sppd.model.repository.PersonRepository;
import pl.pollub.sppd.model.repository.YearRepository;
import pl.pollub.sppd.service.Thesis.ThesisSaveDto;
import pl.pollub.sppd.service.exceptions.GeneralException;

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

    private boolean checkYear(Long id) {
        return yearRepository.findById(id).isPresent();
    }

    private boolean checkLecturer(Long id) {
        Optional<Person> person = personRepository.findById(id);
        return person.isPresent() && person.get().getPermission().equals(Permission.LECTURER);
    }
}
