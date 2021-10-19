package pl.pollub.sppd.api.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
public class ErrorMessage {

    private int statusCode;
    private Date timestamp;
    private String message;
    private List<String> Description;

    public ErrorMessage(int statusCode, Date timestamp, String message) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
    }

    public ErrorMessage(int statusCode, Date timestamp, List<String> description, String message) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.Description = description;
        this.message = message;
    }
}
