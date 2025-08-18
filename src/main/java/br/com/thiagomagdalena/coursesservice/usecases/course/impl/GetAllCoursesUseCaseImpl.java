package br.com.thiagomagdalena.coursesservice.usecases.course.impl;

import br.com.thiagomagdalena.coursesservice.api.dto.course.CourseResponse;
import br.com.thiagomagdalena.coursesservice.api.dto.course.CoursesRequestParams;
import br.com.thiagomagdalena.coursesservice.persistance.repository.CourseRepository;
import br.com.thiagomagdalena.coursesservice.usecases.course.GetAllCoursesUseCase;
import br.com.thiagomagdalena.coursesservice.usecases.course.adapter.CourseResponseAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetAllCoursesUseCaseImpl implements GetAllCoursesUseCase {

    private final CourseRepository courseRepository;
    private final CourseResponseAdapter courseResponseAdapter;

    @Override
    public Flux<CourseResponse> execute(CoursesRequestParams coursesRequestParams) {
        return courseRepository.findByCourseRequestParams(coursesRequestParams)
                .map(courseResponseAdapter::adapt);
    }
}
