package pl.pollub.sppd.model.thesisStatus;

import lombok.Getter;

@Getter
public enum ThesisStatus {

    ADDED_STUDENT(1L),
    ACCEPTED_LECTURER(2L),
    ADDED_LECTURER(3L),
    RESERVED_STUDENT(4L),
    ACCEPTED_FACULTY(5l),
    ARCHIVED(6L),
    REJECTED(7L);

    private Long number;

    ThesisStatus(Long number) {
        this.number = number;
    }
}

