package pl.pollub.sppd.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.pollub.sppd.model.permission.Permission;
import pl.pollub.sppd.model.repository.PersonRepository;
import pl.pollub.sppd.service.exceptions.AlreadyExistsException;
import pl.pollub.sppd.service.exceptions.PermissionException;

@Component
@RequiredArgsConstructor
public class LoginService {

    private final PersonRepository personRepository;

    public void checkAvailableLogin(String login) throws AlreadyExistsException {
        if (personRepository.findPersonByLogin(login) != null) {
            throw new AlreadyExistsException("user with login " + login + " already exists");
        }
    }
}
