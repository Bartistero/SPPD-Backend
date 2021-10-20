package pl.pollub.sppd.api.exception;

import lombok.Getter;
import java.util.Date;

@Getter
public class ErrorMessage extends Error{

    private String message;

    public ErrorMessage(int statusCode, Date timestamp, String message) {
        super(statusCode,timestamp);
        this.message = message;
    }
}
