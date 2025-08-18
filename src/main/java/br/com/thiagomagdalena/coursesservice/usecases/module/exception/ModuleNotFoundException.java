package br.com.thiagomagdalena.coursesservice.usecases.module.exception;

import br.com.thiagomagdalena.coursesservice.api.handler.dto.ErrorDetails;
import br.com.thiagomagdalena.coursesservice.api.handler.exception.NotFoundException;

public class ModuleNotFoundException extends NotFoundException {

    public ModuleNotFoundException() {
        super("Module not found");
    }

    public ModuleNotFoundException(Long id) {
        super("Module with ID " + id + " not found");
    }

    public ModuleNotFoundException(ErrorDetails errorDetails) {
        super(errorDetails);
    }

    public ModuleNotFoundException(String message) {
        super(message);
    }

    public ModuleNotFoundException(ErrorDetails errorDetails, Throwable cause) {
        super(errorDetails, cause);
    }

    public ModuleNotFoundException(String message, String origin) {
        super(message, origin);
    }

    public ModuleNotFoundException(String message, String origin, String code) {
        super(message, origin, code);
    }
}
