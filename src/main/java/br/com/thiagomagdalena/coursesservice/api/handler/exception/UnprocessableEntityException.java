package br.com.thiagomagdalena.coursesservice.api.handler.exception;

import br.com.thiagomagdalena.coursesservice.api.handler.dto.ErrorDetails;
import org.springframework.http.HttpStatus;

public class UnprocessableEntityException extends AbstractApiException {

    public UnprocessableEntityException(String message) {
        super(message);
    }

    public UnprocessableEntityException(ErrorDetails errorDetails) {
        super(errorDetails);
    }

    public UnprocessableEntityException(ErrorDetails errorDetails, Throwable cause) {
        super(errorDetails, cause);
    }

    public UnprocessableEntityException(String message, String origin) {
        super(message, origin);
    }

    public UnprocessableEntityException(String message, String origin, String code) {
        super(message, origin, code);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNPROCESSABLE_ENTITY;
    }

}
