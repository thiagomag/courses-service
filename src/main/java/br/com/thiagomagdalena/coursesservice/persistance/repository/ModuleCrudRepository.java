package br.com.thiagomagdalena.coursesservice.persistance.repository;

import br.com.thiagomagdalena.coursesservice.persistance.domain.Module;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;

import java.util.Set;

public interface ModuleCrudRepository extends BaseReactiveCrudRepository<Module, Long> {

    @Query("SELECT m.* FROM module m " +
            "WHERE ('' = :title OR LOWER(m.title) LIKE LOWER('%'||:title||'%')) " +
            "AND (:courseId IS NULL OR m.course_id = :courseId) " +
            "AND ('' = COALESCE(:ids, '') OR m.id::varchar IN (:ids)) " +
            "AND c.deleted_tmsp IS NULL")
    Flux<Module> findByModuleRequestParams(@Param("title") String title,
                                           @Param("courseId") Long courseId,
                                           @Param("ids") Set<String> ids);

    Flux<Module> findByCourseId(Long courseId);
}
