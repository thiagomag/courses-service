package br.com.thiagomagdalena.coursesservice.api.handler;

import br.com.thiagomagdalena.coursesservice.api.handler.dto.ErrorDetails;
import br.com.thiagomagdalena.coursesservice.api.handler.dto.GeneralErrorResponse;
import br.com.thiagomagdalena.coursesservice.api.handler.exception.AbstractApiException;
import br.com.thiagomagdalena.coursesservice.api.handler.exception.ForbiddenException;
import br.com.thiagomagdalena.coursesservice.configuration.deserializer.exceptions.EnumValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toMap;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @RequestMapping(method = {GET, POST, PUT, DELETE, PATCH}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<Map<String, ErrorDetails>> handleException(final Exception exception) {
        final var message = Optional.ofNullable(exception.getMessage()).orElse("Internal server error");
        return Mono.just(Map.of("error", ErrorDetails.builder()
                .code("500")
                .message(message)
                .origin("COURSES_SERVICE")
                .build()));
    }

    @RequestMapping(method = {GET, POST, PUT, DELETE, PATCH}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ExceptionHandler(AbstractApiException.class)
    public Mono<ResponseEntity<?>> handleAbstractApiException(final AbstractApiException exception) {
        return Mono.just(ResponseEntity.status(exception.getHttpStatus())
                .body(Map.of("error", exception.getErrorDetails())));
    }

    @RequestMapping(method = {GET, POST, PUT, DELETE, PATCH}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public Mono<Map<String, ErrorDetails>> handleAccessDeniedException(final AccessDeniedException exception) {
        final var errorDetails = ErrorDetails.builder()
                .message(Optional.ofNullable(exception.getMessage()).orElse("Forbidden"))
                .build();
        return Mono.just(Map.of("error", errorDetails));
    }

    @RequestMapping(method = {GET, POST, PUT, DELETE, PATCH}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public Mono<Map<String, ErrorDetails>> handleForbiddenException(final ForbiddenException exception) {
        return Mono.just(Map.of("error", exception.getErrorDetails()));
    }

    @RequestMapping(method = {GET, POST, PUT, DELETE, PATCH}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ExceptionHandler(ServerWebInputException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Mono<GeneralErrorResponse> handleServerWebInputException(ServerWebInputException e) {
        return buildGeneralErrorResponse(e.getMessage());
    }

    @RequestMapping(method = {GET, POST, PUT, DELETE, PATCH}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Mono<Map<String, List<String>>> handleWebExchangeBindException(WebExchangeBindException exception) {
        return Mono.just(exception.getBindingResult().getAllErrors().stream()
                .collect(toMap(
                        objectError -> ((FieldError) objectError).getField(),
                        objectError -> {
                            final List<String> errorMsgList = new ArrayList<>();
                            errorMsgList.add(objectError.getDefaultMessage());
                            return errorMsgList;
                        },
                        (errorMsgList1, errorMsgList2) -> errorMsgList1
                )));
    }

    @RequestMapping(method = {GET, POST, PUT, DELETE, PATCH}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ExceptionHandler(EnumValidationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Mono<Map<String, List<String>>> handleEnumValidationException(EnumValidationException exception) {
        final var msg = messageSource.getMessage("invalid.enum.message", new Object[]{exception.getFieldValue()}, Locale.getDefault());
        return Mono.just(Map.of(exception.getFieldName(), Collections.singletonList(msg)));
    }

    private Mono<GeneralErrorResponse> buildGeneralErrorResponse(String detail) {
        return Mono.just(GeneralErrorResponse.builder()
                .detail(detail)
                .build());
    }

}
