package pl.pollub.sppd.model.address;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.pollub.sppd.model.IdModel;
import pl.pollub.sppd.model.Person;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@RequiredArgsConstructor
public class Voivodeship extends IdModel {

    private String voivodeshipCode;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "voivodeship")
    private Set<County> county;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "voivodeship")
    private Set<Person> person;
}
