package br.com.thiagomagdalena.coursesservice.persistance.repository;

import br.com.thiagomagdalena.coursesservice.persistance.domain.Course;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;

import java.util.Set;

public interface CourseCrudRepository extends BaseReactiveCrudRepository<Course, Long> {

    @Query("SELECT c.* FROM courses c " +
            "WHERE ('' = :name OR LOWER(c.name) LIKE LOWER('%'||:name||'%')) " +
            "AND ('' = :category OR LOWER(c.category) = LOWER(:category)) " +
            "AND ('' = COALESCE(:ids, '') OR c.id::varchar IN (:ids)) " +
            "AND c.deleted_tmsp IS NULL")
    Flux<Course> findByCourseRequestParams(@Param("name") String name,
                                           @Param("category") String category,
                                           @Param("ids") Set<String> ids);
}
