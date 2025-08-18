package br.com.thiagomagdalena.coursesservice.persistance.repository.impl;

import br.com.thiagomagdalena.coursesservice.api.dto.lesson.LessonsRequestParams;
import br.com.thiagomagdalena.coursesservice.persistance.domain.Lesson;
import br.com.thiagomagdalena.coursesservice.persistance.repository.BaseReactiveCrudRepository;
import br.com.thiagomagdalena.coursesservice.persistance.repository.LessonCrudRepository;
import br.com.thiagomagdalena.coursesservice.persistance.repository.LessonRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;

import java.util.Collections;
import java.util.Optional;

@Repository
public class LessonRepositoryImpl implements LessonRepository {

    private final LessonCrudRepository lessonCrudRepository;

    public LessonRepositoryImpl(LessonCrudRepository lessonCrudRepository) {
        this.lessonCrudRepository = lessonCrudRepository;
    }

    @Override
    public BaseReactiveCrudRepository<Lesson, Long> getRepository() {
        return lessonCrudRepository;
    }

    @Override
    public Flux<Lesson> findByLessonsRequestParam(LessonsRequestParams lessonsRequestParams) {
        final var title = Optional.ofNullable(lessonsRequestParams.getTitle()).orElse("");
        final var moduleId = lessonsRequestParams.getModuleId();
        final var ids = CollectionUtils.isEmpty(lessonsRequestParams.getIds()) ? Collections.singleton("") : lessonsRequestParams.getIdsAsString();

        return lessonCrudRepository.findByLessonRequestParams(title, moduleId, ids);
    }

    @Override
    public Flux<Lesson> findByModuleId(Long moduleId) {
        return lessonCrudRepository.findByModuleId(moduleId);
    }
}
