package br.com.thiagomagdalena.coursesservice.usecases.module.impl;

import br.com.thiagomagdalena.coursesservice.persistance.repository.ModuleRepository;
import br.com.thiagomagdalena.coursesservice.usecases.module.DeleteModuleUseCase;
import br.com.thiagomagdalena.coursesservice.usecases.module.exception.ModuleNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteModuleUseCaseImpl implements DeleteModuleUseCase {

    private final ModuleRepository moduleRepository;

    @Override
    public Mono<Void> execute(Long moduleId) {
        return moduleRepository.findById(moduleId)
                .switchIfEmpty(Mono.error(new ModuleNotFoundException("Module not found with id: " + moduleId)))
                .flatMap(moduleRepository::softDelete)
                .then();
    }
}
