package br.com.thiagomagdalena.coursesservice.usecases.lesson.impl;

import br.com.thiagomagdalena.coursesservice.api.dto.lesson.LessonRequest;
import br.com.thiagomagdalena.coursesservice.api.dto.lesson.LessonResponse;
import br.com.thiagomagdalena.coursesservice.persistance.repository.LessonRepository;
import br.com.thiagomagdalena.coursesservice.usecases.lesson.UpdateLessonUseCase;
import br.com.thiagomagdalena.coursesservice.usecases.lesson.adapter.LessonAdapter;
import br.com.thiagomagdalena.coursesservice.usecases.lesson.adapter.LessonResponseAdapter;
import br.com.thiagomagdalena.coursesservice.usecases.lesson.exception.LessonNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateLessonUseCaseImpl implements UpdateLessonUseCase {

    private final LessonRepository lessonRepository;
    private final LessonAdapter lessonAdapter;
    private final LessonResponseAdapter lessonResponseAdapter;

    @Override
    public Mono<LessonResponse> execute(LessonRequest lessonRequest) {
        final var lessonId = lessonRequest.getId();
        return lessonRepository.findById(lessonId)
                .switchIfEmpty(Mono.error(new LessonNotFoundException("Lesson not found with id: " + lessonId)))
                .map(lesson -> lessonAdapter.adapt(lessonRequest, lesson))
                .flatMap(lessonRepository::save)
                .map(lessonResponseAdapter::adapt);
    }
}
