package br.com.thiagomagdalena.coursesservice.persistance.repository;

import br.com.thiagomagdalena.coursesservice.api.dto.module.ModulesRequestParams;
import br.com.thiagomagdalena.coursesservice.persistance.domain.Module;
import reactor.core.publisher.Flux;

public interface ModuleRepository extends BaseReactiveComposedCrudRepository<Module, Long> {

    Flux<Module> findByModulesRequestParam(ModulesRequestParams modulesRequestParam);

    Flux<Module> findByCourseIdEager(Long courseId);
}
