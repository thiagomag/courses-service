package br.com.thiagomagdalena.coursesservice.usecases.lesson;

import br.com.thiagomagdalena.coursesservice.api.dto.lesson.LessonResponse;
import br.com.thiagomagdalena.coursesservice.api.dto.lesson.LessonsRequestParams;
import br.com.thiagomagdalena.coursesservice.usecases.UseCase;
import reactor.core.publisher.Flux;

public interface GetAllLessonsUseCase extends UseCase<LessonsRequestParams, Flux<LessonResponse>> {
}
