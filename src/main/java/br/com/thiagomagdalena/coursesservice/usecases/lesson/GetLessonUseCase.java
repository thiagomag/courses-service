package br.com.thiagomagdalena.coursesservice.usecases.lesson;

import br.com.thiagomagdalena.coursesservice.api.dto.lesson.LessonResponse;
import br.com.thiagomagdalena.coursesservice.usecases.UseCase;
import reactor.core.publisher.Mono;

public interface GetLessonUseCase extends UseCase<Long, Mono<LessonResponse>> {
}
