package br.com.thiagomagdalena.coursesservice.persistance.repository;

import br.com.thiagomagdalena.coursesservice.api.dto.course.CoursesRequestParams;
import br.com.thiagomagdalena.coursesservice.persistance.domain.Course;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourseRepository extends BaseReactiveComposedCrudRepository<Course, Long>{

    Flux<Course> findByCourseRequestParams(CoursesRequestParams params);

    Mono<Course> findByIdEager(Long id);
}
