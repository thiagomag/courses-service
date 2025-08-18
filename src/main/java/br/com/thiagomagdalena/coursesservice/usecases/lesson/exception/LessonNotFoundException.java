package br.com.thiagomagdalena.coursesservice.usecases.lesson.exception;

import br.com.thiagomagdalena.coursesservice.api.handler.dto.ErrorDetails;
import br.com.thiagomagdalena.coursesservice.api.handler.exception.NotFoundException;

public class LessonNotFoundException extends NotFoundException {

    public LessonNotFoundException() {
        super("Lesson not found");
    }

    public LessonNotFoundException(Long id) {
        super("Lesson with ID " + id + " not found");
    }

    public LessonNotFoundException(ErrorDetails errorDetails) {
        super(errorDetails);
    }

    public LessonNotFoundException(String message) {
        super(message);
    }

    public LessonNotFoundException(ErrorDetails errorDetails, Throwable cause) {
        super(errorDetails, cause);
    }

    public LessonNotFoundException(String message, String origin) {
        super(message, origin);
    }

    public LessonNotFoundException(String message, String origin, String code) {
        super(message, origin, code);
    }
}
