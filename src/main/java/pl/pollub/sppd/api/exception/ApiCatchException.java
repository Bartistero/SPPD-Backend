package pl.pollub.sppd.api.exception;

import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import pl.pollub.sppd.service.GeneralException;

import java.util.Date;

@ControllerAdvice
public class ApiCatchException {

    @ExceptionHandler(value = {GeneralException.class, InvalidDataAccessResourceUsageException.class})
    public ResponseEntity<ErrorMessage> handleNoSuchElementFoundException(GeneralException ex, WebRequest request) {
        ErrorMessage errorMessage;
        if (ex.getMessageList() == null) {
            errorMessage = new ErrorMessage(HttpStatus.NOT_ACCEPTABLE.value(), new Date(), ex.getMessage());
        } else {
            errorMessage = new ErrorMessage(HttpStatus.NOT_ACCEPTABLE.value(), new Date(), ex.getMessageList(), "the following errors were found");
        }
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_ACCEPTABLE);
    }
}
