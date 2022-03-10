package pl.pollub.sppd.model.year;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.pollub.sppd.model.IdModel;
import pl.pollub.sppd.model.ThesisTitle.ThesisTitle;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Getter
@Setter
public class Year extends IdModel {

    String year;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "year", cascade= CascadeType.ALL)
    private Set<ThesisTitle> thesisTitle;

    public Year() {
    }

    public Year(String year) {
        this.year = year;
    }
}
