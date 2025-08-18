package br.com.thiagomagdalena.coursesservice.usecases.lesson;

import br.com.thiagomagdalena.coursesservice.usecases.UseCase;
import reactor.core.publisher.Mono;

public interface DeleteLessonUseCase extends UseCase<Long, Mono<Void>> {
}
