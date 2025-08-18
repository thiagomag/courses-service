package br.com.thiagomagdalena.coursesservice.usecases.course;

import br.com.thiagomagdalena.coursesservice.usecases.UseCase;
import reactor.core.publisher.Mono;

public interface DeleteCourseUseCase extends UseCase<Long, Mono<Void>> {
}
