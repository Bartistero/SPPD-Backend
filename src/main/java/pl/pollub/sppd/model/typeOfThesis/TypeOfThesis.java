package pl.pollub.sppd.model.typeOfThesis;

import lombok.Getter;

@Getter
public enum TypeOfThesis {

    ENGINEERING(1L),
    MASTER(2l),
    DOCTORAL(3L);

    private Long number;

    TypeOfThesis(Long number) {
        this.number = number;
    }
}
