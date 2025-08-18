package br.com.thiagomagdalena.coursesservice.usecases.course.exception;

import br.com.thiagomagdalena.coursesservice.api.handler.dto.ErrorDetails;
import br.com.thiagomagdalena.coursesservice.api.handler.exception.NotFoundException;

public class CourseNotFoundException extends NotFoundException {

    public CourseNotFoundException() {
        super("Course not found");
    }

    public CourseNotFoundException(Long id) {
        super("Course with ID " + id + " not found");
    }

    public CourseNotFoundException(ErrorDetails errorDetails) {
        super(errorDetails);
    }

    public CourseNotFoundException(String message) {
        super(message);
    }

    public CourseNotFoundException(ErrorDetails errorDetails, Throwable cause) {
        super(errorDetails, cause);
    }

    public CourseNotFoundException(String message, String origin) {
        super(message, origin);
    }

    public CourseNotFoundException(String message, String origin, String code) {
        super(message, origin, code);
    }
}
