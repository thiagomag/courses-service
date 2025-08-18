package br.com.thiagomagdalena.coursesservice.usecases.course;

import br.com.thiagomagdalena.coursesservice.api.dto.course.CourseResponse;
import br.com.thiagomagdalena.coursesservice.api.dto.course.CourseRequest;
import br.com.thiagomagdalena.coursesservice.usecases.UseCase;
import reactor.core.publisher.Mono;

public interface CreateCourseUseCase extends UseCase<CourseRequest, Mono<CourseResponse>> {
}
