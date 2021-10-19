package pl.pollub.sppd.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pollub.sppd.model.Person;
import pl.pollub.sppd.model.accountStatus.AccountStatus;
import pl.pollub.sppd.model.permission.Permission;
import pl.pollub.sppd.model.repository.PersonRepository;
import pl.pollub.sppd.service.CheckPersonalData;
import pl.pollub.sppd.service.GeneralException;
import pl.pollub.sppd.service.address.*;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AddressVerification addressVerification;
    private final CheckPersonalData checkPersonalData;
    private final PersonRepository personRepository;

    public AdminDto add(AdminDto adminDto, String authorities) throws GeneralException {
        if (authorities.equals(Permission.SUPER_ADMIN.toString())) {
            checkPersonalData.checkValidData(adminDto);
            checkAddress(adminDto);
            personRepository.save(AdminDto.AdminDtoToPerson(adminDto));
        } else {
            throw new GeneralException("User with " + authorities + " can not use this API!");
        }
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
