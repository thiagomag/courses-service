package br.com.thiagomagdalena.coursesservice.api.handler.exception;

import br.com.thiagomagdalena.coursesservice.api.handler.dto.ErrorDetails;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@Getter
public abstract class AbstractApiException extends RuntimeException {

    private static final String DEFAULT_ABSTRACT_API_EXCEPTION_MESSAGE = "Abstract Api Exception!";
    private final ErrorDetails errorDetails;

    protected AbstractApiException(ErrorDetails errorDetails ) {
        super( Optional.ofNullable(errorDetails.getMessage()).orElse(DEFAULT_ABSTRACT_API_EXCEPTION_MESSAGE) );
        this.errorDetails = errorDetails;
    }

    protected AbstractApiException(ErrorDetails errorDetails, Throwable cause ) {
        super( Optional.ofNullable(errorDetails.getMessage()).orElse(DEFAULT_ABSTRACT_API_EXCEPTION_MESSAGE), cause );
        this.errorDetails = errorDetails;
    }

    protected AbstractApiException(String message ) {
        super( Optional.ofNullable( message ).orElse(DEFAULT_ABSTRACT_API_EXCEPTION_MESSAGE) );
        this.errorDetails = ErrorDetails.builder().message(message).build();
    }

    protected AbstractApiException(String message, Throwable cause ) {
        super( Optional.ofNullable( message ).orElse(DEFAULT_ABSTRACT_API_EXCEPTION_MESSAGE), cause );
        this.errorDetails = ErrorDetails.builder().message(message).build();
    }

    protected AbstractApiException(String message, String origin, Throwable cause ) {
        super( Optional.ofNullable( message ).orElse(DEFAULT_ABSTRACT_API_EXCEPTION_MESSAGE), cause );
        this.errorDetails = ErrorDetails.builder().origin(origin).message(message).build();
    }

    protected AbstractApiException(String message, String origin ) {
        super( Optional.ofNullable( message ).orElse(DEFAULT_ABSTRACT_API_EXCEPTION_MESSAGE) );
        this.errorDetails = ErrorDetails.builder().origin(origin).message(message).build();
    }

    protected AbstractApiException(String message, String origin, String code ) {
        super( Optional.ofNullable( message ).orElse(DEFAULT_ABSTRACT_API_EXCEPTION_MESSAGE) );
        this.errorDetails = ErrorDetails.builder().origin(origin).message(message).code(code).build();
    }

    protected AbstractApiException(String message, String origin, String code, List<Object> details ) {
        super( Optional.ofNullable( message ).orElse(DEFAULT_ABSTRACT_API_EXCEPTION_MESSAGE) );
        this.errorDetails = ErrorDetails.builder().origin(origin).message(message).code(code).details(details).build();
    }

    protected AbstractApiException(String message, String origin, String code, List<Object> details, Throwable cause ) {
        super( Optional.ofNullable( message ).orElse(DEFAULT_ABSTRACT_API_EXCEPTION_MESSAGE), cause );
        this.errorDetails = ErrorDetails.builder().origin(origin).message(message).code(code).details(details).build();
    }

    public abstract HttpStatus getHttpStatus();

}
