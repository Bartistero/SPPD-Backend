package pl.pollub.sppd.model.permission;

import lombok.Getter;

@Getter
public enum Permission {
    SUPER_ADMIN(1L),
    ADMIN(2L),
    LECTURER(3L),
    STUDENT(4L);

    private Long number;

    private Permission(Long number) {
        this.number = number;
    }
}
