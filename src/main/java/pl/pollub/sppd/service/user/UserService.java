package pl.pollub.sppd.service.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pollub.sppd.mail.Mail;
import pl.pollub.sppd.model.Person;
import pl.pollub.sppd.model.faculty.Faculty;
import pl.pollub.sppd.model.permission.Permission;
import pl.pollub.sppd.model.repository.PersonRepository;
import pl.pollub.sppd.service.CheckPersonalData;
import pl.pollub.sppd.service.GenerateToken;
import pl.pollub.sppd.service.PermissionVerification;
import pl.pollub.sppd.service.PersonAbstractDto;
import pl.pollub.sppd.service.address.AddressVerification;
import pl.pollub.sppd.service.exceptions.GeneralException;
import pl.pollub.sppd.service.exceptions.NotFoundException;
import pl.pollub.sppd.service.faculty.FacultyVerification;

import javax.mail.MessagingException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final AddressVerification addressVerification;
    private final CheckPersonalData checkPersonalData;
    private final FacultyVerification facultyVerification;
    private final PersonRepository personRepository;
    private final Mail mail;

    public List<UserDto> get(Permission permission, String login) {
        Long facultyId = personRepository.findPersonByLogin(login).getFaculty().getId();
        List<Person> personList = personRepository.findPersonByPermissionAndFaculty(permission, facultyId);
        return personList.stream()
                .map(UserDto::personToUserDto)
                .collect(Collectors.toList());
    }

    public UserSaveDto add(UserSaveDto userSaveDto, String login) throws GeneralException, NotFoundException, MessagingException {
        checkPersonalData.validData(userSaveDto);
        checkData(userSaveDto);
        String token = GenerateToken.randomGenerator(80);
        userSaveDto.setActiveToken(token);
        Faculty faculty = personRepository.findPersonByLogin(login).getFaculty();
        personRepository.save(UserSaveDto.userSaveDtoToPerson(userSaveDto,faculty));
        mail.sendMail(userSaveDto.getEmail(), token, userSaveDto.getLogin());
        return userSaveDto;
    }

    public UserDto update(UserDto userDto) throws GeneralException, NotFoundException {
        checkPersonalData.validUpdateData(userDto);
        checkData(userDto);
        personRepository.save(UserDto.userDtoToPerson(userDto));
        return userDto;
    }

    private void checkData(PersonAbstractDto adminDto) throws GeneralException, NotFoundException {
        addressVerification.checkAvailabilityAddress(
                adminDto.getCountryDto(),
                adminDto.getVoivodeshipDto(),
                adminDto.getCountyDto(),
                adminDto.getBoroughDto(),
                adminDto.getCityDto(),
                adminDto.getStreetDto());

        PermissionVerification.checkPermission(adminDto.getPermission());
    }
}