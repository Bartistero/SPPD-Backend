package pl.pollub.sppd.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pollub.sppd.mail.Mail;
import pl.pollub.sppd.model.permission.Permission;
import pl.pollub.sppd.model.repository.PersonRepository;
import pl.pollub.sppd.service.CheckPermission;
import pl.pollub.sppd.service.CheckPersonalData;
import pl.pollub.sppd.service.exceptions.GeneralException;
import pl.pollub.sppd.service.address.*;
import pl.pollub.sppd.service.exceptions.PermissionException;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AddressVerification addressVerification;
    private final CheckPersonalData checkPersonalData;
    private final PersonRepository personRepository;
    private final CheckPermission checkPermission;
    private final Mail mail;

    public AdminDto add(AdminDto adminDto, Permission authorities) throws GeneralException, PermissionException {
            checkPermission.checkPermission(Permission.SUPER_ADMIN, authorities);
            checkPersonalData.checkValidData(adminDto);
            checkAddress(adminDto);
            personRepository.save(AdminDto.AdminDtoToPerson(adminDto));

        return adminDto;
    }

    private void checkAddress(AdminDto adminDto) throws GeneralException {
        addressVerification.checkAvailabilityAddress(
                adminDto.getCountryDto(),
                adminDto.getVoivodeshipDto(),
                adminDto.getCountyDto(),
                adminDto.getBoroughDto(),
                adminDto.getCityDto(),
                adminDto.getStreetDto());
    }
}
