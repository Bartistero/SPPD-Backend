package pl.pollub.sppd.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import pl.pollub.sppd.service.exceptions.AlreadyExistsException;
import pl.pollub.sppd.service.exceptions.GeneralException;
import pl.pollub.sppd.service.exceptions.NotFoundException;
import pl.pollub.sppd.service.exceptions.PermissionException;

import java.util.Date;

@ControllerAdvice
public class ApiCatchException {

    @ExceptionHandler(value = GeneralException.class)
    public ResponseEntity<? extends Error> handleGeneralException(GeneralException ex, WebRequest request) {
        Error error;
        if (ex.getMessageList() == null) {
            error = new ErrorMessage(HttpStatus.NOT_ACCEPTABLE.value(), new Date(), ex.getMessage());
            return new ResponseEntity<ErrorMessage>((ErrorMessage) error, HttpStatus.NOT_ACCEPTABLE);
        } else {
            error = new ErrorMessageList(HttpStatus.NOT_ACCEPTABLE.value(), new Date(), ex.getMessageList());
            return new ResponseEntity<ErrorMessageList>((ErrorMessageList) error, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundException ex, WebRequest request) {
        ErrorMessage error = new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), ex.getMessage());
        return new ResponseEntity<ErrorMessage>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = PermissionException.class)
    public ResponseEntity<ErrorMessage> handlePermissionException(PermissionException ex, WebRequest request) {
        ErrorMessage error = new ErrorMessage(HttpStatus.CONFLICT.value(), new Date(), ex.getMessage());
        return new ResponseEntity<ErrorMessage>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = AlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> handlePermissionException(AlreadyExistsException ex, WebRequest request) {
        ErrorMessage error = new ErrorMessage(HttpStatus.CONFLICT.value(), new Date(), ex.getMessage());
        return new ResponseEntity<ErrorMessage>(error, HttpStatus.CONFLICT);
    }
}
