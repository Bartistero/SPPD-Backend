package pl.pollub.sppd.model.sex;

import lombok.Getter;

@Getter
public enum Sex {

    MEN(1L),
    WOMAN(2L),
    OTHER(3L);

    private Long number;

    Sex(Long number) {
        this.number = number;
    }
}
