package pl.pollub.sppd.api.exception;

import java.util.Date;

public abstract class Error {

    private int statusCode;
    private Date timestamp;

    public Error(int statusCode, Date timestamp) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
    }
}
