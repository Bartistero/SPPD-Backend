package pl.pollub.sppd.model.faculty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.pollub.sppd.model.IdModel;
import pl.pollub.sppd.model.Person;
import pl.pollub.sppd.model.ThesisTitle.ThesisTitle;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Faculty extends IdModel {

    String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "faculty")
    private Set<DegreeCourse> degreeCourses;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "faculty", cascade=CascadeType.ALL)
    private Set<Person> person;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "faculty", cascade= CascadeType.ALL)
    private Set<ThesisTitle> thesisTitle;
}
