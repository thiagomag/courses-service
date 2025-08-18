package br.com.thiagomagdalena.coursesservice.usecases.course.impl;

import br.com.thiagomagdalena.coursesservice.api.dto.course.CourseResponse;
import br.com.thiagomagdalena.coursesservice.persistance.repository.CourseRepository;
import br.com.thiagomagdalena.coursesservice.usecases.course.GetCourseUseCase;
import br.com.thiagomagdalena.coursesservice.usecases.course.adapter.CourseResponseAdapter;
import br.com.thiagomagdalena.coursesservice.usecases.course.exception.CourseNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetCourseUseCaseImpl implements GetCourseUseCase {

    private final CourseRepository courseRepository;
    private final CourseResponseAdapter courseResponseAdapter;

    @Override
    public Mono<CourseResponse> execute(Long courseId) {
        return courseRepository.findByIdEager(courseId)
                .switchIfEmpty(Mono.error(new CourseNotFoundException(courseId)))
                .map(courseResponseAdapter::adapt)
                .doOnError(error -> log.error("Error to get course with id {}: {}", courseId, error.getMessage()));
    }
}