package pl.pollub.sppd.model.faculty;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.pollub.sppd.model.IdModel;
import pl.pollub.sppd.model.ThesisTitle.ThesisTitle;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class DegreeCourse extends IdModel {

    String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idFaculty", nullable = false)
    Faculty faculty;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "degreeCourse")
    private Set<ThesisTitle> thesisTitles;
}
