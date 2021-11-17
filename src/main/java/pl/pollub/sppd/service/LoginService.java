package pl.pollub.sppd.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.pollub.sppd.model.Person;
import pl.pollub.sppd.model.accountStatus.AccountStatus;
import pl.pollub.sppd.model.permission.Permission;
import pl.pollub.sppd.model.repository.PersonRepository;
import pl.pollub.sppd.security.BlockUserDto;
import pl.pollub.sppd.security.LoginCredentials;
import pl.pollub.sppd.service.exceptions.AlreadyExistsException;
import pl.pollub.sppd.service.exceptions.NotFoundException;
import pl.pollub.sppd.service.exceptions.PermissionException;
import pl.pollub.sppd.service.user.UserSaveDto;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LoginService {

    private final PersonRepository personRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void checkAvailableLogin(String login) throws AlreadyExistsException {
        if (personRepository.findPersonByLogin(login) != null) {
            throw new AlreadyExistsException("user with login " + login + " already exists");
        }
    }

    @Transactional
    public void activate(String token, LoginCredentials login) throws NotFoundException {
        Person person = personRepository.findUserByActivateToken(token)
                .orElseThrow(() -> new NotFoundException(
                        "user with the given token don't exists"));
        person.setAccountStatus(AccountStatus.ACTIVE);
        person.setPassword(passwordEncoder.encode(login.getPassword()));
        personRepository.save(person);
    }

    public List<BlockUserDto> getBlockUser() {
        List<Person> personList = personRepository.findBlockPerson(AccountStatus.SUSPENDED);
        return personList.stream()
                .map(BlockUserDto::personToBlockUserDto)
                .collect(Collectors.toList());
    }
}
