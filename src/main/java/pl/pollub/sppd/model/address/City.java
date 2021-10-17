package pl.pollub.sppd.model.address;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.pollub.sppd.model.IdModel;

import javax.persistence.*;


@Entity
@Getter
@RequiredArgsConstructor
public class City extends IdModel {

    private int voivodeshipCode;
    private int CountyCode;
    private int BoroughCode;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idBorough", nullable=false)
    private Borough borough;
}
