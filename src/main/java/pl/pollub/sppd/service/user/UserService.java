package pl.pollub.sppd.service.user;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.pollub.sppd.mail.Mail;
import pl.pollub.sppd.model.Person;
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
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class UserService {

    private final AddressVerification addressVerification;
    private final CheckPersonalData checkPersonalData;
    private final FacultyVerification facultyVerification;
    private final PersonRepository personRepository;
    private final Mail mail;

    public List<UserDto> get(Integer page, Integer size) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        int pageSize = size != null && size > 0 ? size : 1;
        List<Person> personList = personRepository.findPersonByPermission(Permission.LECTURER, PageRequest.of(pageNumber, pageSize));
        personList = Stream.concat(personList.stream(), personRepository.findPersonByPermission
                (Permission.STUDENT, PageRequest.of(pageNumber, pageSize)).stream())
                .collect(Collectors.toList());
        return personList.stream()
                .map(UserDto::personToUserDto)
                .collect(Collectors.toList());
    }

    public UserSaveDto add(UserSaveDto userSaveDto) throws GeneralException, NotFoundException, MessagingException {
        checkPersonalData.validData(userSaveDto);
        checkData(userSaveDto);
        String token = GenerateToken.randomGenerator(80);
        userSaveDto.setActiveToken(token);
        personRepository.save(UserSaveDto.userSaveDtoToPerson(userSaveDto));
        mail.sendMail(userSaveDto.getEmail(), token, userSaveDto.getLogin());
        return userSaveDto;
    }

    private void checkData(PersonAbstractDto adminDto) throws GeneralException, NotFoundException {
        addressVerification.checkAvailabilityAddress(
                adminDto.getCountryDto(),
                adminDto.getVoivodeshipDto(),
                adminDto.getCountyDto(),
                adminDto.getBoroughDto(),
                adminDto.getCityDto(),
                adminDto.getStreetDto());

        facultyVerification.checkAvailabilityFaculty(adminDto.getFacultyDto().getId());
        PermissionVerification.checkPermission(adminDto.getPermission());
    }
}