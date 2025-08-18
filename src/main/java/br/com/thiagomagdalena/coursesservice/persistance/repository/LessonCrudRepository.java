package br.com.thiagomagdalena.coursesservice.persistance.repository;

import br.com.thiagomagdalena.coursesservice.persistance.domain.Lesson;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;

import java.util.Set;

public interface LessonCrudRepository extends BaseReactiveCrudRepository<Lesson, Long> {

    @Query("SELECT l.* FROM lesson l " +
            "WHERE ('' = :title OR LOWER(l.title) LIKE LOWER('%'||:title||'%')) " +
            "AND (:moduleId IS NULL OR l.course_id = :moduleId) " +
            "AND ('' = COALESCE(:ids, '') OR l.id::varchar IN (:ids)) " +
            "AND l.deleted_tmsp IS NULL")
    Flux<Lesson> findByLessonRequestParams(@Param("title") String title,
                                           @Param("moduleId") Long moduleId,
                                           @Param("ids") Set<String> ids);

    Flux<Lesson> findByModuleId(Long moduleId);
}
