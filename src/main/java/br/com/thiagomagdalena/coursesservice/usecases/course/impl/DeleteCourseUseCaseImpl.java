package br.com.thiagomagdalena.coursesservice.usecases.course.impl;

import br.com.thiagomagdalena.coursesservice.persistance.repository.CourseRepository;
import br.com.thiagomagdalena.coursesservice.usecases.course.DeleteCourseUseCase;
import br.com.thiagomagdalena.coursesservice.usecases.course.exception.CourseNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteCourseUseCaseImpl implements DeleteCourseUseCase {

    private final CourseRepository courseRepository;

    @Override
    public Mono<Void> execute(Long courseId) {
        return courseRepository.findById(courseId)
                .switchIfEmpty(Mono.error(new CourseNotFoundException("Course not found with id: " + courseId)))
                .flatMap(courseRepository::softDelete)
                .then();
    }
}
