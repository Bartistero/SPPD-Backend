package pl.pollub.sppd.model.permission;

import lombok.Getter;

@Getter
public enum Permission {
    SUPER_ADMIN(0L),
    ADMIN(1L),
    LECTURER(2L),
    STUDENT(3L);

    private Long number;

    private Permission(Long number) {
        this.number = number;
    }
}
