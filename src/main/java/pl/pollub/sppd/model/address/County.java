package pl.pollub.sppd.model.address;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.pollub.sppd.model.IdModel;

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
    Set<Borough> borough;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idVoivodeship", nullable = false)
    Voivodeship voivodeship;
}
