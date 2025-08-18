package br.com.thiagomagdalena.coursesservice.usecases.lesson.impl;

import br.com.thiagomagdalena.coursesservice.api.dto.lesson.LessonRequest;
import br.com.thiagomagdalena.coursesservice.api.dto.lesson.LessonResponse;
import br.com.thiagomagdalena.coursesservice.persistance.repository.LessonRepository;
import br.com.thiagomagdalena.coursesservice.usecases.lesson.CreateLessonUseCase;
import br.com.thiagomagdalena.coursesservice.usecases.lesson.adapter.LessonAdapter;
import br.com.thiagomagdalena.coursesservice.usecases.lesson.adapter.LessonResponseAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateLessonUseCaseImpl implements CreateLessonUseCase {

    private final LessonRepository lessonRepository;
    private final LessonAdapter lessonAdapter;
    private final LessonResponseAdapter lessonResponseAdapter;

    @Override
    public Mono<LessonResponse> execute(LessonRequest lessonRequest) {
        final var lesson = lessonAdapter.adapt(lessonRequest);
        return lessonRepository.save(lesson)
                .map(lessonResponseAdapter::adapt)
                .doOnSuccess(cr -> log.info("Lesson created with id: {}", cr.getId()))
                .doOnError(e -> log.error("Error creating lesson: {}", e.getMessage(), e));
    }
}
