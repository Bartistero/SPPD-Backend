package pl.pollub.sppd.service.admin;

import pl.pollub.sppd.model.Person;
import pl.pollub.sppd.service.PersonAbstractDto;


public class AdminSaveDto extends PersonAbstractDto {

    public static Person adminDtoToPerson(AdminSaveDto adminSaveDto) {
        return adminAbstractDtoToPerson(adminSaveDto);
    }
}
