package br.com.thiagomagdalena.coursesservice.configuration.deserializer.exceptions;

import lombok.Getter;

@Getter
public class EnumValidationException extends RuntimeException {

    private final String fieldName;
    private final String fieldValue;

    public EnumValidationException(String fieldName, String fieldValue, String message) {
        super(message);
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public EnumValidationException(String fieldName, String fieldValue, String message, Throwable cause) {
        super(message, cause);
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public EnumValidationException(String fieldName, String fieldValue, Throwable cause) {
        super(cause);
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

}