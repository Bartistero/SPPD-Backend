package pl.pollub.sppd.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Person {

    @Id
    private long idPerson;
    private String name;
    private String middleName;
    private String surname;
    private String login;
    private String email;
    private String password;
    private String albumNumber; 
    private String pesel; 
    private String phone;
    //klucze obce
    private String houseNumber;
    private String flatNumber;

}
