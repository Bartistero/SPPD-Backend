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
public class Street extends IdModel {

    private int voivodeshipCode;
    private int countyCode;
    private int boroughCode;
    private String characteristic;
    private String firstPart;
    private String secondPart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idBorough", nullable = false)
    private Borough borough;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "street")
    private Set<Person> person;
}
