package pl.pollub.sppd.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pollub.sppd.model.accountStatus.AccountStatus;
import pl.pollub.sppd.model.accountStatus.AccountStatusConverter;
import pl.pollub.sppd.model.address.*;
import pl.pollub.sppd.model.permission.Permission;
import pl.pollub.sppd.model.permission.PermissionConverter;
import pl.pollub.sppd.model.sex.Sex;
import pl.pollub.sppd.model.sex.SexConverter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Person")
@NoArgsConstructor
public class Person extends IdModel {

    private String name;
    private String middleName;
    private String surname;
    private String login;
    private String email;
    private String password;
    private String albumNumber;
    private String pesel;
    private String phone;

    @Column(name = "idPermissions")
    @Convert(converter = PermissionConverter.class)
    private Permission permission;

    @Column(name = "idAccountStatus")
    @Convert(converter = AccountStatusConverter.class)
    private AccountStatus accountStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCountry", nullable = false)
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idVoivodeship", nullable = false)
    private Voivodeship voivodeship;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCounty", nullable = false)
    private County county;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idBorough", nullable = false)
    private Borough borough;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCity", nullable = false)
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idStreet", nullable = false)
    private Street street;

    @Column(name = "idSex")
    @Convert(converter = SexConverter.class)
    private Sex sex;

    private String houseNumber;
    private String flatNumber;

}
