package br.com.thiagomagdalena.coursesservice.usecases.course;

import br.com.thiagomagdalena.coursesservice.api.dto.course.CourseResponse;
import br.com.thiagomagdalena.coursesservice.usecases.UseCase;
import reactor.core.publisher.Mono;

public interface GetCourseUseCase extends UseCase<Long, Mono<CourseResponse>> {
}
