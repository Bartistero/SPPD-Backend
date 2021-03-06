package pl.pollub.sppd.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pollub.sppd.mail.Mail;
import pl.pollub.sppd.model.Person;
import pl.pollub.sppd.model.permission.Permission;
import pl.pollub.sppd.model.repository.PersonRepository;
import pl.pollub.sppd.service.CheckPersonalData;
import pl.pollub.sppd.service.GenerateToken;
import pl.pollub.sppd.service.PermissionVerification;
import pl.pollub.sppd.service.PersonAbstractDto;
import pl.pollub.sppd.service.exceptions.GeneralException;
import pl.pollub.sppd.service.address.*;
import pl.pollub.sppd.service.exceptions.NotFoundException;
import pl.pollub.sppd.service.faculty.FacultyVerification;

import javax.mail.MessagingException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AddressVerification addressVerification;
    private final CheckPersonalData checkPersonalData;
    private final FacultyVerification facultyVerification;
    private final PersonRepository personRepository;
    private final Mail mail;

    public List<AdminDto> get() {
        List<Person> personList = personRepository.findPersonByPermission(Permission.ADMIN);
        return personList.stream()
                .map(AdminDto::personToAdminDto)
                .collect(Collectors.toList());
    }

    public AdminSaveDto add(AdminSaveDto adminSaveDto) throws GeneralException, MessagingException, NotFoundException {
        checkPersonalData.validData(adminSaveDto);
        checkData(adminSaveDto);
        facultyVerification.checkAvailabilityFaculty(adminSaveDto.getFacultyDto().getId());
        String token = GenerateToken.randomGenerator(80);
        adminSaveDto.setActiveToken(token);
        personRepository.save(AdminSaveDto.adminDtoToPerson(adminSaveDto));
        mail.sendMailActivate(adminSaveDto.getEmail(), token, adminSaveDto.getLogin());
        return adminSaveDto;
    }

    public AdminDto update(AdminDto adminDto) throws GeneralException, NotFoundException {
        checkPersonalData.validUpdateData(adminDto);
        checkData(adminDto);
        personRepository.save(AdminDto.adminDtoToPerson(adminDto));
        return adminDto;
    }

    public void delete(Long id) throws NotFoundException, GeneralException {
        if (id == null)
            throw new GeneralException("Field id can not be null!");
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Admin with id: " + id + " don't exists"));
        personRepository.delete(person);
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
