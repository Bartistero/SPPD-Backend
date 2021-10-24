package pl.pollub.sppd.service.exceptions;

import java.util.function.Supplier;

public class AlreadyExistsException extends Exception {

    public AlreadyExistsException(String message) {
        super(message);
    }
}
