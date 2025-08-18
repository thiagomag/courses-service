package br.com.thiagomagdalena.coursesservice.persistance.repository.impl;

import br.com.thiagomagdalena.coursesservice.api.dto.course.CoursesRequestParams;
import br.com.thiagomagdalena.coursesservice.persistance.domain.Course;
import br.com.thiagomagdalena.coursesservice.persistance.repository.BaseReactiveCrudRepository;
import br.com.thiagomagdalena.coursesservice.persistance.repository.CourseCrudRepository;
import br.com.thiagomagdalena.coursesservice.persistance.repository.CourseRepository;
import br.com.thiagomagdalena.coursesservice.persistance.repository.ModuleRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Optional;

@Repository
public class CourseRepositoryImpl implements CourseRepository {

    private final CourseCrudRepository courseCrudRepository;
    private final ModuleRepository moduleRepository;

    public CourseRepositoryImpl(CourseCrudRepository courseCrudRepository,
                                @Lazy ModuleRepository moduleRepository) {
        this.courseCrudRepository = courseCrudRepository;
        this.moduleRepository = moduleRepository;
    }

    @Override
    public BaseReactiveCrudRepository<Course, Long> getRepository() {
        return courseCrudRepository;
    }

    @Override
    public Flux<Course> findByCourseRequestParams(CoursesRequestParams params) {
        final var name = Optional.ofNullable(params.getName()).orElse("");
        final var category = Optional.ofNullable(params.getCategory()).map(Enum::name).orElse("");
        final var ids = CollectionUtils.isEmpty(params.getIds()) ? Collections.singleton("") : params.getIdsAsString();
        return courseCrudRepository.findByCourseRequestParams(name, category, ids)
                .flatMap(this::fetchModules);
    }

    @Override
    public Mono<Course> findByIdEager(Long id) {
        return courseCrudRepository.findById(id)
                .flatMap(this::fetchModules);
    }

    private Mono<Course> fetchModules(Course course) {
        return moduleRepository.findByCourseIdEager(course.getId())
                .collectList()
                .map(modules -> {
                    course.setModules(modules);
                    return course;
                });
    }
}