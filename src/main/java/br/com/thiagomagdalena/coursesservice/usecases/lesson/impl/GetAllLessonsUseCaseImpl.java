package br.com.thiagomagdalena.coursesservice.usecases.lesson.impl;

import br.com.thiagomagdalena.coursesservice.api.dto.lesson.LessonResponse;
import br.com.thiagomagdalena.coursesservice.api.dto.lesson.LessonsRequestParams;
import br.com.thiagomagdalena.coursesservice.persistance.repository.LessonRepository;
import br.com.thiagomagdalena.coursesservice.usecases.lesson.GetAllLessonsUseCase;
import br.com.thiagomagdalena.coursesservice.usecases.lesson.adapter.LessonResponseAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetAllLessonsUseCaseImpl implements GetAllLessonsUseCase {

    private final LessonRepository repository;
    private final LessonResponseAdapter responseAdapter;

    @Override
    public Flux<LessonResponse> execute(LessonsRequestParams lessonsRequestParams) {
        return repository.findByLessonsRequestParam(lessonsRequestParams)
                .map(responseAdapter::adapt);
    }
}
