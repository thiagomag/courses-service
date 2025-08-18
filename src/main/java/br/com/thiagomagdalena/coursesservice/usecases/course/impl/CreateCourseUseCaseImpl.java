package br.com.thiagomagdalena.coursesservice.usecases.course.impl;

import br.com.thiagomagdalena.coursesservice.api.dto.course.CourseResponse;
import br.com.thiagomagdalena.coursesservice.api.dto.course.CourseRequest;
import br.com.thiagomagdalena.coursesservice.persistance.repository.CourseRepository;
import br.com.thiagomagdalena.coursesservice.usecases.course.CreateCourseUseCase;
import br.com.thiagomagdalena.coursesservice.usecases.course.adapter.CourseAdapter;
import br.com.thiagomagdalena.coursesservice.usecases.course.adapter.CourseResponseAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateCourseUseCaseImpl implements CreateCourseUseCase {

    private final CourseRepository courseRepository;
    private final CourseAdapter courseAdapter;
    private final CourseResponseAdapter courseResponseAdapter;

    @Override
    public Mono<CourseResponse> execute(CourseRequest courseRequest) {
        final var course = courseAdapter.adapt(courseRequest);
        return courseRepository.save(course)
                .map(courseResponseAdapter::adapt)
                .doOnSuccess(cr -> log.info("Course created with id: {}", cr.getId()))
                .doOnError(e -> log.error("Error creating course: {}", e.getMessage(), e));
    }
}
