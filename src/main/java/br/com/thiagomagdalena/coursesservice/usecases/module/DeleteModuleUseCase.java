package br.com.thiagomagdalena.coursesservice.usecases.module;

import br.com.thiagomagdalena.coursesservice.usecases.UseCase;
import reactor.core.publisher.Mono;

public interface DeleteModuleUseCase extends UseCase<Long, Mono<Void>> {
}
