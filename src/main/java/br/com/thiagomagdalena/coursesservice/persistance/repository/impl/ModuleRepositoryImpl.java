package br.com.thiagomagdalena.coursesservice.persistance.repository.impl;

import br.com.thiagomagdalena.coursesservice.api.dto.module.ModulesRequestParams;
import br.com.thiagomagdalena.coursesservice.persistance.domain.Module;
import br.com.thiagomagdalena.coursesservice.persistance.repository.BaseReactiveCrudRepository;
import br.com.thiagomagdalena.coursesservice.persistance.repository.LessonRepository;
import br.com.thiagomagdalena.coursesservice.persistance.repository.ModuleCrudRepository;
import br.com.thiagomagdalena.coursesservice.persistance.repository.ModuleRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Optional;

@Repository
public class ModuleRepositoryImpl implements ModuleRepository {

    private final ModuleCrudRepository moduleCrudRepository;
    private final LessonRepository lessonRepository;

    public ModuleRepositoryImpl(ModuleCrudRepository moduleCrudRepository,
                                @Lazy LessonRepository lessonRepository) {
        this.moduleCrudRepository = moduleCrudRepository;
        this.lessonRepository = lessonRepository;
    }

    @Override
    public BaseReactiveCrudRepository<Module, Long> getRepository() {
        return moduleCrudRepository;
    }

    @Override
    public Flux<Module> findByModulesRequestParam(ModulesRequestParams modulesRequestParam) {
        final var title = Optional.ofNullable(modulesRequestParam.getTitle()).orElse("");
        final var courseId = modulesRequestParam.getCourseId();
        final var ids = CollectionUtils.isEmpty(modulesRequestParam.getIds()) ? Collections.singleton("") : modulesRequestParam.getIdsAsString();

        return moduleCrudRepository.findByModuleRequestParams(title, courseId, ids)
                .flatMap(this::fetchDependencies);
    }

    @Override
    public Flux<Module> findByCourseIdEager(Long courseId) {
        return moduleCrudRepository.findByCourseId(courseId)
                .flatMap(this::fetchDependencies);
    }

    private Mono<Module> fetchDependencies(Module module) {
        return lessonRepository.findByModuleId(module.getId())
                .collectList()
                .map(lessons -> {
                    module.setLessons(lessons);
                    return module;
                });
    }
}
