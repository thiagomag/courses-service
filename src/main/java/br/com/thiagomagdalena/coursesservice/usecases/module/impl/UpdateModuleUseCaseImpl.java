package br.com.thiagomagdalena.coursesservice.usecases.module.impl;

import br.com.thiagomagdalena.coursesservice.api.dto.module.ModuleRequest;
import br.com.thiagomagdalena.coursesservice.api.dto.module.ModuleResponse;
import br.com.thiagomagdalena.coursesservice.persistance.repository.ModuleRepository;
import br.com.thiagomagdalena.coursesservice.usecases.module.UpdateModuleUseCase;
import br.com.thiagomagdalena.coursesservice.usecases.module.adapter.ModuleAdapter;
import br.com.thiagomagdalena.coursesservice.usecases.module.adapter.ModuleResponseAdapter;
import br.com.thiagomagdalena.coursesservice.usecases.module.exception.ModuleNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateModuleUseCaseImpl implements UpdateModuleUseCase {

    private final ModuleRepository moduleRepository;
    private final ModuleAdapter moduleAdapter;
    private final ModuleResponseAdapter moduleResponseAdapter;

    @Override
    public Mono<ModuleResponse> execute(ModuleRequest moduleRequest) {
        final var moduleId = moduleRequest.getId();
        return moduleRepository.findById(moduleId)
                .switchIfEmpty(Mono.error(new ModuleNotFoundException("Module not found with id: " + moduleId)))
                .map(module -> moduleAdapter.adapt(moduleRequest, module))
                .flatMap(moduleRepository::save)
                .map(moduleResponseAdapter::adapt);
    }
}
