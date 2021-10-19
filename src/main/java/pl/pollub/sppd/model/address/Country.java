package pl.pollub.sppd.model.address;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pollub.sppd.model.IdModel;
import pl.pollub.sppd.model.Person;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Country extends IdModel {

    private String country;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
    private Set<Person> person;
}
