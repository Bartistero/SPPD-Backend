package pl.pollub.sppd.model.ThesisTitle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import pl.pollub.sppd.model.IdModel;
import pl.pollub.sppd.model.Person;
import pl.pollub.sppd.model.faculty.Faculty;
import pl.pollub.sppd.model.thesisStatus.ThesisStatus;
import pl.pollub.sppd.model.thesisStatus.ThesisStatusConverter;
import pl.pollub.sppd.model.typeOfThesis.TypeOfThesis;
import pl.pollub.sppd.model.typeOfThesis.TypeOfThesisConverter;
import pl.pollub.sppd.model.year.Year;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
/*//To suppress serializing properties with null values
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
//Ignoring new fields on JSON objects
@JsonIgnoreProperties(ignoreUnknown = true)*/
public class ThesisTitle extends IdModel {

    String thesisName;
    String description;
    Integer amountPeople;

    @Column(name = "idTypeOfThesis")
    @Convert(converter = TypeOfThesisConverter.class)
    TypeOfThesis typeOfThesis;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idYear", nullable = false)
    Year year;

    @Column(name = "idThesisStatus")
    @Convert(converter = ThesisStatusConverter.class)
    ThesisStatus thesisStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idFaculty", nullable = false)
    Faculty faculty;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "Person_has_ThesisTitle",
            joinColumns = {@JoinColumn(name = "idThesisTitle")},
            inverseJoinColumns = {@JoinColumn(name = "idPerson")})
    Set<Person> listOfPersonThesis;
}
