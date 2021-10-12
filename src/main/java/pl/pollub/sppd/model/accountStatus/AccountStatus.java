package pl.pollub.sppd.model.accountStatus;

import lombok.Getter;

@Getter
public enum AccountStatus {
    ACTIVE(0L),
    DISABLE(1L),
    SUSPENDED(2L);

    private Long number ;

    private AccountStatus(Long number){
        this.number = number;
    }
}
