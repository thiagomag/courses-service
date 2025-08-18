package br.com.thiagomagdalena.coursesservice.usecases.lesson;

import br.com.thiagomagdalena.coursesservice.api.dto.lesson.LessonRequest;
import br.com.thiagomagdalena.coursesservice.api.dto.lesson.LessonResponse;
import br.com.thiagomagdalena.coursesservice.usecases.UseCase;
import reactor.core.publisher.Mono;

public interface CreateLessonUseCase extends UseCase<LessonRequest, Mono<LessonResponse>> {
}
