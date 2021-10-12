package pl.pollub.sppd.model.accountStatus;

import lombok.Getter;

@Getter
public enum AccountStatus {
    ACTIVE(1L),
    DISABLE(2L),
    SUSPENDED(3L);

    private Long number ;

    private AccountStatus(Long number){
        this.number = number;
    }
}
