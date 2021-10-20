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
public class City extends IdModel {

    private int voivodeshipCode;
    private int CountyCode;
    private int BoroughCode;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idBorough", nullable = false)
    private Borough borough;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "city")
    private Set<Person> person;
}
