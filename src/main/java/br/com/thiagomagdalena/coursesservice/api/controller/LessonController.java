package br.com.thiagomagdalena.coursesservice.api.controller;

import br.com.thiagomagdalena.coursesservice.api.dto.lesson.LessonRequest;
import br.com.thiagomagdalena.coursesservice.api.dto.lesson.LessonResponse;
import br.com.thiagomagdalena.coursesservice.api.dto.lesson.LessonsRequestParams;
import br.com.thiagomagdalena.coursesservice.usecases.lesson.CreateLessonUseCase;
import br.com.thiagomagdalena.coursesservice.usecases.lesson.DeleteLessonUseCase;
import br.com.thiagomagdalena.coursesservice.usecases.lesson.GetAllLessonsUseCase;
import br.com.thiagomagdalena.coursesservice.usecases.lesson.GetLessonUseCase;
import br.com.thiagomagdalena.coursesservice.usecases.lesson.UpdateLessonUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lessons")
@Tag(name = "Lições",description = "Api de gerenciamento de lições")
public class LessonController {

    private final CreateLessonUseCase createLessonUseCase;
    private final GetAllLessonsUseCase getAllLessonsUseCase;
    private final GetLessonUseCase getLessonUseCase;
    private final UpdateLessonUseCase updateLessonUseCase;
    private final DeleteLessonUseCase deleteLessonUseCase;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @Operation(summary = "Criar Lição", description = "Endpoint para criar uma nova lição")
    public Mono<LessonResponse> createCourse(@Valid @RequestBody LessonRequest lessonRequest) {
        return createLessonUseCase.execute(lessonRequest);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUBSCRIBER')")
    @Operation(summary = "Listar Lições", description = "Endpoint para listar todos as lições")
    public Flux<LessonResponse> fetchAll(@RequestParam(value = "id__in", required = false) String ids,
                                         @RequestParam(value = "title", required = false) String title,
                                         @RequestParam(value = "module_id", required = false) Long moduleId) {
        final var params = LessonsRequestParams.builder()
                .title(title)
                .moduleId(moduleId)
                .build();
        params.addIds(ids);
        return getAllLessonsUseCase.execute(params);
    }

    @GetMapping("/{lessonId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUBSCRIBER')")
    @Operation(summary = "Obter lição por ID", description = "Endpoint para obter uma lição pelo seu ID")
    public Mono<LessonResponse> fetchById(@PathVariable Long lessonId) {
        return getLessonUseCase.execute(lessonId);
    }

    @PatchMapping("/{lessonId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @Operation(summary = "Atualizar Lição", description = "Endpoint para atualizar uma lição existente")
    public Mono<LessonResponse> updateLesson(@PathVariable Long lessonId,
                                             @RequestBody LessonRequest lessonRequest) {
        lessonRequest.setId(lessonId);
        return updateLessonUseCase.execute(lessonRequest);
    }

    @DeleteMapping("/{lessonId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @Operation(summary = "Deletar Lição", description = "Endpoint para deletar uma lição existente")
    public Mono<Void> deleteLesson(@PathVariable Long lessonId) {
        return deleteLessonUseCase.execute(lessonId);
    }
}
