package pl.pollub.sppd.api.exception;

import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class ErrorMessageList extends Error {

    private final List<String> description;

    public ErrorMessageList(int statusCode, Date timestamp, List<String> description) {
        super(statusCode, timestamp);
        this.description = description;
    }
}
