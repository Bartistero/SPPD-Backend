package pl.pollub.sppd.model.address;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pollub.sppd.model.IdModel;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Country extends IdModel {

    private String name;
}
