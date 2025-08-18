package br.com.thiagomagdalena.coursesservice.usecases.course.impl;

import br.com.thiagomagdalena.coursesservice.api.dto.course.CourseRequest;
import br.com.thiagomagdalena.coursesservice.api.dto.course.CourseResponse;
import br.com.thiagomagdalena.coursesservice.persistance.repository.CourseRepository;
import br.com.thiagomagdalena.coursesservice.usecases.course.UpdateCourseUseCase;
import br.com.thiagomagdalena.coursesservice.usecases.course.adapter.CourseAdapter;
import br.com.thiagomagdalena.coursesservice.usecases.course.adapter.CourseResponseAdapter;
import br.com.thiagomagdalena.coursesservice.usecases.course.exception.CourseNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateCourseUseCaseImpl implements UpdateCourseUseCase {

    private final CourseRepository courseRepository;
    private final CourseAdapter courseAdapter;
    private final CourseResponseAdapter courseResponseAdapter;

    @Override
    public Mono<CourseResponse> execute(CourseRequest courseRequest) {
        final var courseId = courseRequest.getId();
        return courseRepository.findById(courseId)
                .switchIfEmpty(Mono.error(new CourseNotFoundException("Course not found with id: " + courseId)))
                .map(course -> courseAdapter.adapt(courseRequest, course))
                .flatMap(courseRepository::save)
                .map(courseResponseAdapter::adapt);
    }
}