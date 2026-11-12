package guard.agro.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.stream.Collectors;

@Log4j2
@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler
    public ResponseEntity<DefaultErrorMessage> NotFoundException(NotFoundException e) {
        var error = new DefaultErrorMessage(HttpStatus.NOT_FOUND.value(), e.getReason());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler
    public ResponseEntity<DefaultErrorMessage> EmailAlreadyExistsException(EmailAlreadyExistsException e) {
        var error = new DefaultErrorMessage(HttpStatus.BAD_REQUEST.value(), e.getReason());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler
    public ResponseEntity<DefaultErrorMessage> CpfAlreadyExistsException(CpfAlreadyExistsException e) {
        var error = new DefaultErrorMessage(HttpStatus.BAD_REQUEST.value(), e.getReason());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler
    public ResponseEntity<DefaultErrorMessage> MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var message = e.getBindingResult().getFieldErrors().stream()
                .map(f -> f.getDefaultMessage())
                .collect(Collectors.joining(", "));

        var error = new DefaultErrorMessage(HttpStatus.BAD_REQUEST.value(), message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler
    public ResponseEntity<DefaultErrorMessage> HttpMessageNotReadableException(HttpMessageNotReadableException e) {
        var error = new DefaultErrorMessage(HttpStatus.BAD_REQUEST.value(), "malformed request body or invalid value");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler
    public ResponseEntity<DefaultErrorMessage> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        var message = String.format("invalid value for parameter '%s'", e.getName());

        var error = new DefaultErrorMessage(HttpStatus.BAD_REQUEST.value(), message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler
    public ResponseEntity<DefaultErrorMessage> DataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error("data integrity violation", e);

        var error = new DefaultErrorMessage(HttpStatus.CONFLICT.value(), "data integrity violation");

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler
    public ResponseEntity<DefaultErrorMessage> NoResourceFoundException(NoResourceFoundException e) {
        var error = new DefaultErrorMessage(HttpStatus.NOT_FOUND.value(), "resource not found");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler
    public ResponseEntity<DefaultErrorMessage> Exception(Exception e) {
        log.error("unexpected error", e);

        var error = new DefaultErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), "internal server error");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}