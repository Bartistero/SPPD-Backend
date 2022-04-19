package pl.pollub.sppd.service.exceptions;

import lombok.Getter;

import java.util.List;
import java.util.function.Supplier;

@Getter
public class GeneralException extends Exception {

    private List<String> messageList;

    public GeneralException(String message) {
        super(message);
    }

    public GeneralException(List<String> message) {
        this.messageList = message;
    }
}
