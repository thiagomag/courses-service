package br.com.thiagomagdalena.coursesservice.usecases.lesson.impl;

import br.com.thiagomagdalena.coursesservice.api.dto.lesson.LessonResponse;
import br.com.thiagomagdalena.coursesservice.persistance.repository.LessonRepository;
import br.com.thiagomagdalena.coursesservice.usecases.lesson.GetLessonUseCase;
import br.com.thiagomagdalena.coursesservice.usecases.lesson.adapter.LessonResponseAdapter;
import br.com.thiagomagdalena.coursesservice.usecases.lesson.exception.LessonNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetLessonUseCaseImpl implements GetLessonUseCase {

    private final LessonRepository lessonRepository;
    private final LessonResponseAdapter lessonResponseAdapter;

    @Override
    public Mono<LessonResponse> execute(Long lessonId) {
        return lessonRepository.findById(lessonId)
                .switchIfEmpty(Mono.error(new LessonNotFoundException(lessonId)))
                .map(lessonResponseAdapter::adapt)
                .doOnError(error -> log.error("Error to get course with id {}: {}", lessonId, error.getMessage()));
    }
}
