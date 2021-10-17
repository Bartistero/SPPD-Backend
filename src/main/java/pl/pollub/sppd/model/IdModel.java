package pl.pollub.sppd.model;

import lombok.Getter;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass
public class IdModel {

    @Id
    private Long id;
}
