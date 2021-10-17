package pl.pollub.sppd.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pollub.sppd.model.accountStatus.AccountStatus;
import pl.pollub.sppd.model.accountStatus.AccountStatusConverter;
import pl.pollub.sppd.model.permission.Permission;
import pl.pollub.sppd.model.permission.PermissionConverter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Person")
@NoArgsConstructor
public class Person extends IdModel{

    private String name;
    private String middleName;
    private String surname;
    private String login;
    private String email;
    private String password;
    private String albumNumber;
    private String pesel;
    private String phone;

    @Column(name = "idAccountStatus")
    @Convert(converter = AccountStatusConverter.class)
    private AccountStatus accountStatus;

    @Column(name = "idPermissions")
    @Convert(converter = PermissionConverter.class)
    private Permission permission;


    private String houseNumber;
    private String flatNumber;

}
