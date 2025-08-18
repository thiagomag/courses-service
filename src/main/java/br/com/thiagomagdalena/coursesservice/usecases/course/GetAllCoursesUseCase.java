package br.com.thiagomagdalena.coursesservice.usecases.course;

import br.com.thiagomagdalena.coursesservice.api.dto.course.CourseResponse;
import br.com.thiagomagdalena.coursesservice.api.dto.course.CoursesRequestParams;
import br.com.thiagomagdalena.coursesservice.usecases.UseCase;
import reactor.core.publisher.Flux;

public interface GetAllCoursesUseCase extends UseCase<CoursesRequestParams, Flux<CourseResponse>> {
}
