package pl.pollub.sppd.model.address;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.pollub.sppd.model.IdModel;
import pl.pollub.sppd.model.Person;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@Entity
@NoArgsConstructor
public class Country extends IdModel {

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
    private Set<Person> person;
}
