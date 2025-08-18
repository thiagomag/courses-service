package br.com.thiagomagdalena.coursesservice.usecases.module.impl;

import br.com.thiagomagdalena.coursesservice.api.dto.module.ModuleResponse;
import br.com.thiagomagdalena.coursesservice.api.dto.module.ModulesRequestParams;
import br.com.thiagomagdalena.coursesservice.persistance.repository.ModuleRepository;
import br.com.thiagomagdalena.coursesservice.usecases.module.GetAllModulesUseCase;
import br.com.thiagomagdalena.coursesservice.usecases.module.adapter.ModuleResponseAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetAllModulesUseCaseImpl implements GetAllModulesUseCase {

    private final ModuleRepository moduleRepository;
    private final ModuleResponseAdapter moduleResponseAdapter;

    @Override
    public Flux<ModuleResponse> execute(ModulesRequestParams modulesRequestParams) {
        return moduleRepository.findByModulesRequestParam(modulesRequestParams)
                .map(moduleResponseAdapter::adapt);
    }
}
