package br.com.thiagomagdalena.coursesservice.usecases.module;

import br.com.thiagomagdalena.coursesservice.api.dto.module.ModuleResponse;
import br.com.thiagomagdalena.coursesservice.api.dto.module.ModulesRequestParams;
import br.com.thiagomagdalena.coursesservice.usecases.UseCase;
import reactor.core.publisher.Flux;

public interface GetAllModulesUseCase extends UseCase<ModulesRequestParams, Flux<ModuleResponse>> {
}
