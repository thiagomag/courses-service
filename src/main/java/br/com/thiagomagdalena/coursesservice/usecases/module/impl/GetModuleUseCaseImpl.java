package br.com.thiagomagdalena.coursesservice.usecases.module.impl;

import br.com.thiagomagdalena.coursesservice.api.dto.module.ModuleResponse;
import br.com.thiagomagdalena.coursesservice.persistance.repository.ModuleRepository;
import br.com.thiagomagdalena.coursesservice.usecases.module.GetModuleUseCase;
import br.com.thiagomagdalena.coursesservice.usecases.module.adapter.ModuleResponseAdapter;
import br.com.thiagomagdalena.coursesservice.usecases.module.exception.ModuleNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetModuleUseCaseImpl implements GetModuleUseCase {

    private final ModuleRepository moduleRepository;
    private final ModuleResponseAdapter moduleResponseAdapter;

    @Override
    public Mono<ModuleResponse> execute(Long moduleId) {
        return moduleRepository.findById(moduleId)
                .switchIfEmpty(Mono.error(new ModuleNotFoundException(moduleId)))
                .map(moduleResponseAdapter::adapt)
                .doOnError(error -> log.error("Error to get module with id {}: {}", moduleId, error.getMessage()));

    }
}
