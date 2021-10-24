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
public class Borough extends IdModel {

    private int voivodeshipCode;
    private int CountyCode;
    private int BoroughCode;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "borough")
    private Set<City> city;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "borough")
    private Set<Street> street;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCounty", nullable = false)
    private County county;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "borough")
    private Set<Person> person;
}
