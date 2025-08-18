package br.com.thiagomagdalena.coursesservice.usecases.lesson.impl;

import br.com.thiagomagdalena.coursesservice.persistance.repository.LessonRepository;
import br.com.thiagomagdalena.coursesservice.usecases.lesson.DeleteLessonUseCase;
import br.com.thiagomagdalena.coursesservice.usecases.lesson.exception.LessonNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteLessonUseCaseImpl implements DeleteLessonUseCase {

    private final LessonRepository lessonRepository;

    @Override
    public Mono<Void> execute(Long lessonId) {
        return lessonRepository.findById(lessonId)
                .switchIfEmpty(Mono.error(new LessonNotFoundException("Lesson not found with id: " + lessonId)))
                .flatMap(lessonRepository::softDelete)
                .then();
    }
}
