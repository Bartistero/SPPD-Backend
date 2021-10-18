package pl.pollub.sppd.service.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import pl.pollub.sppd.model.Person;
import pl.pollub.sppd.model.accountStatus.AccountStatus;
import pl.pollub.sppd.model.address.Borough;
import pl.pollub.sppd.model.permission.Permission;
import pl.pollub.sppd.service.address.*;

@Getter
@Setter
@NoArgsConstructor
public class AdminDto {

    //private long idPerson;
    private String name;
    private String middleName;
    private String surname;
    private String login;
    private String email;
    private String pesel;
    private String phone;
    private Permission permission;
    private AccountStatus accountStatus;
    private CountryDto countryDto;
    private VoivodeshipDto voivodeshipDto;
    private CountyDto countyDto;
    private BoroughDto boroughDto;
    private CityDto cityDto;
    private StreetDto streetDto;
    private String houseNumber;
    private String flatNumber;


    public static void AdminDtoToPerson(AdminDto adminDto, Person person){
        BeanUtils.copyProperties(adminDto,person,"albumNumber");
    }
}
