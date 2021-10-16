package pl.pollub.sppd.service;

import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pl.pollub.sppd.model.Person;
import pl.pollub.sppd.model.accountStatus.AccountStatus;
import pl.pollub.sppd.model.permission.Permission;

@Getter
public class AdminDto {

    private long idPerson;
    private String name;
    private String middleName;
    private String surname;
    private String login;
    private String email;
    private String pesel;
    private String phone;
    private AccountStatus accountStatus;
    private Permission permission;
    private String houseNumber;
    private String flatNumber;

    public static void AdminDtoToPerson(AdminDto adminDto, Person person){
        BeanUtils.copyProperties(adminDto,person,"albumNumber");
    }
}
