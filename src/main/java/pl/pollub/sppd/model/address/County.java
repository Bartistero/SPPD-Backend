package pl.pollub.sppd.model.address;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.pollub.sppd.model.IdModel;
import pl.pollub.sppd.model.Person;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@RequiredArgsConstructor
public class County extends IdModel {

    private int voivodeshipCode;
    private int CountyCode;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "county")
    private Set<Borough> borough;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idVoivodeship", nullable = false)
    private Voivodeship voivodeship;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "county")
    private Set<Person> person;
}
