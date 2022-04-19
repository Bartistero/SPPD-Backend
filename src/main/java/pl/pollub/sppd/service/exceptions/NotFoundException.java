package pl.pollub.sppd.service.exceptions;

import lombok.Getter;

import java.util.function.Supplier;

@Getter
public class NotFoundException extends Exception  {

    public NotFoundException(String message) {
        super(message);
    }

}

