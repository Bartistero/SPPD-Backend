package pl.pollub.sppd.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.pollub.sppd.model.Person;
import pl.pollub.sppd.model.repository.PersonRepository;
import pl.pollub.sppd.service.admin.AdminDto;
import pl.pollub.sppd.service.exceptions.GeneralException;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CheckPersonalData {

    private final PersonRepository personRepository;

    public void checkValidData(PersonAbstractDto admin) throws GeneralException {

        List<String> errors = new ArrayList<>();
        if (admin.getName() == null) {
            errors.add("Field name can not be null");
        }

        if (admin.getSurname() == null) {
            errors.add("Field surname can not be null");
        }

        if (admin.getLogin() == null) {
            errors.add("Field login can not be null");
        } else if (checkAvailabilityLogin(admin.getLogin())) {
            errors.add("User with login = " + admin.getLogin() + " already exists");
        }

        if (admin.getEmail() == null) {
            errors.add("Field email can not be null");
        } else if (checkAvailabilityEmail(admin.getEmail())) {
            errors.add("User with email = " + admin.getEmail() + " already exists");
        }

        if (admin.getPesel() == null) {
            errors.add("Field PESEL can not be null");
        } else if (validatePesel(admin.getPesel())) {
            errors.add("Field PESEL must include 11 charts or user with this pesel already exists!");
        }
        if (admin.getHouseNumber() == null) {
            errors.add("Field House Number can not be null");
        }

        if (errors.size() > 0)
            throw new GeneralException(errors);
    }

    private boolean checkAvailabilityLogin(String login) {
        return personRepository.findPersonByLogin(login) != null;
    }

    private boolean checkAvailabilityEmail(String email) {
        return personRepository.findPersonByEmail(email) != null;
    }

    private boolean validatePesel(String pesel) {
        return pesel.length() != 11 || personRepository.findPersonByPesel(pesel) != null;
    }
}
