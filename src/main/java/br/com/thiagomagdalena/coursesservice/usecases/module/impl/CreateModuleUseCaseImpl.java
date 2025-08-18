package br.com.thiagomagdalena.coursesservice.usecases.module.impl;

import br.com.thiagomagdalena.coursesservice.api.dto.module.ModuleRequest;
import br.com.thiagomagdalena.coursesservice.api.dto.module.ModuleResponse;
import br.com.thiagomagdalena.coursesservice.persistance.repository.ModuleRepository;
import br.com.thiagomagdalena.coursesservice.usecases.module.CreateModuleUseCase;
import br.com.thiagomagdalena.coursesservice.usecases.module.adapter.ModuleAdapter;
import br.com.thiagomagdalena.coursesservice.usecases.module.adapter.ModuleResponseAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateModuleUseCaseImpl implements CreateModuleUseCase {

    private final ModuleRepository moduleRepository;
    private final ModuleAdapter moduleAdapter;
    private final ModuleResponseAdapter moduleResponseAdapter;

    @Override
    public Mono<ModuleResponse> execute(ModuleRequest moduleRequest) {
        final var module = moduleAdapter.adapt(moduleRequest);
        return moduleRepository.save(module)
                .map(moduleResponseAdapter::adapt)
                .doOnSuccess(mr -> log.info("Module created with id: {}", mr.getId()))
                .doOnError(e -> log.error("Error creating module: {}", e.getMessage(), e));
    }
}
