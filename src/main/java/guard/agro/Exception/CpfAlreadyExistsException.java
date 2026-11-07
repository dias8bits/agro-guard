package guard.agro.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CpfAlreadyExistsException extends ResponseStatusException {
    public CpfAlreadyExistsException (String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
