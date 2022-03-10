package pl.pollub.sppd.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.pollub.sppd.mail.Mail;
import pl.pollub.sppd.model.Person;
import pl.pollub.sppd.model.accountStatus.AccountStatus;
import pl.pollub.sppd.model.repository.PersonRepository;
import pl.pollub.sppd.security.BlockUserDto;
import pl.pollub.sppd.security.LoginCredentials;
import pl.pollub.sppd.service.exceptions.AlreadyExistsException;
import pl.pollub.sppd.service.exceptions.NotFoundException;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LoginService {

    private final PersonRepository personRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final Mail mail;

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
        person.setLoginAttempts(0);
        person.setActivateToken("");
        personRepository.save(person);
    }

    public List<BlockUserDto> getBlockUser() {
        List<Person> personList = personRepository.findBlockPerson(AccountStatus.SUSPENDED);
        return personList.stream()
                .map(BlockUserDto::personToBlockUserDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public BlockUserDto updateBlockUser(BlockUserDto user) throws MessagingException {
        Person person = personRepository.findPersonByLogin(user.getUserName());
        String token = GenerateToken.randomGenerator(80);
        mail.sendMailActivate(person.getEmail(), token, person.getLogin());
        person.setActivateToken(token);
        personRepository.save(BlockUserDto.blockUserDtoToPerson(user,person));
        return user;
    }

    public AccountStatus getAccountStatus(String login){
        return personRepository.findPersonByLogin(login).getAccountStatus();
    }
}
