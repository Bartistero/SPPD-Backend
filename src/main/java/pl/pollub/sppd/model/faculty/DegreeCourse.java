package pl.pollub.sppd.model.faculty;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.pollub.sppd.model.IdModel;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class DegreeCourse extends IdModel {

    String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idFaculty", nullable = false)
    Faculty faculty;
}
