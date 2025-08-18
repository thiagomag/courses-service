package br.com.thiagomagdalena.coursesservice.persistance.repository;

import br.com.thiagomagdalena.coursesservice.api.dto.lesson.LessonsRequestParams;
import br.com.thiagomagdalena.coursesservice.persistance.domain.Lesson;
import reactor.core.publisher.Flux;

public interface LessonRepository extends BaseReactiveComposedCrudRepository<Lesson, Long> {

    Flux<Lesson> findByLessonsRequestParam(LessonsRequestParams lessonsRequestParams);

    Flux<Lesson> findByModuleId(Long moduleId);
}
