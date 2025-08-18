package br.com.thiagomagdalena.coursesservice.api.handler.exception;

import br.com.thiagomagdalena.coursesservice.api.handler.dto.ErrorDetails;
import org.springframework.http.HttpStatus;

public class ForbiddenException extends AbstractApiException {

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(ErrorDetails errorDetails) {
        super(errorDetails);
    }

    public ForbiddenException(ErrorDetails errorDetails, Throwable cause) {
        super(errorDetails, cause);
    }

    public ForbiddenException(String message, String origin) {
        super(message, origin);
    }

    public ForbiddenException(String message, String origin, String code) {
        super(message, origin, code);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.FORBIDDEN;
    }
}
