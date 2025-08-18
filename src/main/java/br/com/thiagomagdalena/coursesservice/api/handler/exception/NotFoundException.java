package br.com.thiagomagdalena.coursesservice.api.handler.exception;

import br.com.thiagomagdalena.coursesservice.api.handler.dto.ErrorDetails;
import org.springframework.http.HttpStatus;

public class NotFoundException extends AbstractApiException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(ErrorDetails errorDetails) {
        super(errorDetails);
    }

    public NotFoundException(ErrorDetails errorDetails, Throwable cause) {
        super(errorDetails, cause);
    }

    public NotFoundException(String message, String origin) {
        super(message, origin);
    }

    public NotFoundException(String message, String origin, String code) {
        super(message, origin, code);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

}
