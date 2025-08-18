package br.com.thiagomagdalena.coursesservice.api.handler.exception;

import br.com.thiagomagdalena.coursesservice.api.handler.dto.ErrorDetails;
import org.springframework.http.HttpStatus;

public class BadRequestException extends AbstractApiException {

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(ErrorDetails errorDetails) {
        super(errorDetails);
    }

    public BadRequestException(ErrorDetails errorDetails, Throwable cause) {
        super(errorDetails, cause);
    }

    public BadRequestException(String message, String origin) {
        super(message, origin);
    }

    public BadRequestException(String message, String origin, String code) {
        super(message, origin, code);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

}
