package pl.pollub.sppd.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pollub.sppd.model.Person;
import pl.pollub.sppd.model.accountStatus.AccountStatus;
import pl.pollub.sppd.model.permission.Permission;
import pl.pollub.sppd.model.sex.Sex;
import pl.pollub.sppd.service.address.*;
import pl.pollub.sppd.service.admin.AdminDto;
import pl.pollub.sppd.service.faculty.FacultyDto;

@Getter
@Setter
@NoArgsConstructor
public abstract class PersonAbstractDto {

    private String name;
    private String middleName;
    private String surname;
    private String login;
    private String email;
    private String pesel;
    private String phone;
    private Permission permission;
    private CountryDto countryDto;
    private VoivodeshipDto voivodeshipDto;
    private CountyDto countyDto;
    private BoroughDto boroughDto;
    private CityDto cityDto;
    private StreetDto streetDto;
    private Sex sex;
    private String houseNumber;
    private String flatNumber;
    private FacultyDto facultyDto;

    protected static Person adminAbstractDtoToPerson(PersonAbstractDto adminDto) {

        Person person = new Person();
        person.setName(adminDto.getName());
        person.setMiddleName(adminDto.getMiddleName());
        person.setSurname(adminDto.getSurname());
        person.setLogin(adminDto.getLogin());
        person.setEmail(adminDto.getEmail());
        person.setPesel(adminDto.getPesel());
        person.setPhone(adminDto.getPhone());
        person.setPermission(adminDto.getPermission());
        person.setAccountStatus(AccountStatus.DISABLE);
        person.setCountry(CountryDto.countryDtoToCountry(adminDto.getCountryDto()));
        person.setVoivodeship(VoivodeshipDto.voivodeshipDtoToVoivodeship(adminDto.getVoivodeshipDto()));
        person.setCounty(CountyDto.countyDtoToCounty(adminDto.getCountyDto()));
        person.setBorough(BoroughDto.boroughDtoToBorough(adminDto.getBoroughDto()));
        person.setCity(CityDto.cityDtoToCity(adminDto.getCityDto()));
        person.setStreet(StreetDto.streetDtoToStreet(adminDto.getStreetDto()));
        person.setSex(adminDto.getSex());
        person.setHouseNumber(adminDto.getHouseNumber());
        person.setFlatNumber(adminDto.getFlatNumber());
        person.setFaculty(FacultyDto.facultyDtoToFaculty(adminDto.getFacultyDto()));

        return person;
    }

    protected static PersonAbstractDto personToAdminAbstractDto(Person person, PersonAbstractDto personAbstractDto) {

        personAbstractDto.setName(person.getName());
        personAbstractDto.setMiddleName(person.getMiddleName());
        personAbstractDto.setSurname(person.getSurname());
        personAbstractDto.setLogin(person.getLogin());
        personAbstractDto.setEmail(person.getEmail());
        personAbstractDto.setPesel(person.getPesel());
        personAbstractDto.setPhone(person.getPhone());
        personAbstractDto.setPermission(person.getPermission());
        personAbstractDto.setCountryDto(CountryDto.countryToCountryDto(person.getCountry()));
        personAbstractDto.setVoivodeshipDto(VoivodeshipDto.voivodeshipToVoivodeshipDto(person.getVoivodeship()));
        personAbstractDto.setCountyDto(CountyDto.countyToCountyDto(person.getCounty()));
        personAbstractDto.setBoroughDto(BoroughDto.boroughToBoroughDto(person.getBorough()));
        personAbstractDto.setCityDto(CityDto.cityToCityDto(person.getCity()));
        personAbstractDto.setStreetDto(StreetDto.streetToStreetDto(person.getStreet()));
        personAbstractDto.setSex(person.getSex());
        personAbstractDto.setHouseNumber(person.getHouseNumber());
        personAbstractDto.setFlatNumber(person.getFlatNumber());
        personAbstractDto.setFacultyDto(FacultyDto.facultyToFacultyDto(person.getFaculty()));
        return personAbstractDto;
    }
}