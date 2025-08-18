package br.com.thiagomagdalena.coursesservice.usecases.module;

import br.com.thiagomagdalena.coursesservice.api.dto.module.ModuleRequest;
import br.com.thiagomagdalena.coursesservice.api.dto.module.ModuleResponse;
import br.com.thiagomagdalena.coursesservice.usecases.UseCase;
import reactor.core.publisher.Mono;

public interface UpdateModuleUseCase extends UseCase<ModuleRequest, Mono<ModuleResponse>> {
}
