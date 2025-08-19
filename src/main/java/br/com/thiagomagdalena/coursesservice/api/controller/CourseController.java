package br.com.thiagomagdalena.coursesservice.api.controller;

import br.com.thiagomagdalena.coursesservice.api.dto.course.CourseResponse;
import br.com.thiagomagdalena.coursesservice.api.dto.course.CoursesRequestParams;
import br.com.thiagomagdalena.coursesservice.api.dto.course.CourseRequest;
import br.com.thiagomagdalena.coursesservice.enums.CourseCategoryEnum;
import br.com.thiagomagdalena.coursesservice.usecases.course.CreateCourseUseCase;
import br.com.thiagomagdalena.coursesservice.usecases.course.DeleteCourseUseCase;
import br.com.thiagomagdalena.coursesservice.usecases.course.GetAllCoursesUseCase;
import br.com.thiagomagdalena.coursesservice.usecases.course.GetCourseUseCase;
import br.com.thiagomagdalena.coursesservice.usecases.course.UpdateCourseUseCase;
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
@RequestMapping("/courses")
@Tag(name = "Cursos",description = "Api de gerenciamento de cursos")
public class CourseController {

    private final CreateCourseUseCase createCourseUseCase;
    private final GetAllCoursesUseCase getAllCoursesUseCase;
    private final GetCourseUseCase getCourseUseCase;
    private final UpdateCourseUseCase updateCourseUseCase;
    private final DeleteCourseUseCase deleteCourseUseCase;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @Operation(summary = "Criar curso", description = "Endpoint para criar um novo curso")
    public Mono<CourseResponse> createCourse(@Valid @RequestBody CourseRequest courseRequest) {
        return createCourseUseCase.execute(courseRequest);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUBSCRIBER')")
    @Operation(summary = "Listar Cursos", description = "Endpoint para listar todos os cursos")
    public Flux<CourseResponse> fetchAll(@RequestParam(value = "id__in", required = false) String ids,
                                         @RequestParam(value = "name", required = false) String name,
                                         @RequestParam(value = "category", required = false) String category) {

        final var params = CoursesRequestParams.builder()
                .name(name)
                .category(CourseCategoryEnum.findBy(category))
                .build();
        params.addIds(ids);
        return getAllCoursesUseCase.execute(params);
    }

    @GetMapping("/{courseId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUBSCRIBER')")
    @Operation(summary = "Obter curso por ID", description = "Endpoint para obter um curso pelo seu ID")
    public Mono<CourseResponse> fetchById(@PathVariable Long courseId) {
        return getCourseUseCase.execute(courseId);
    }

    @PatchMapping("/{courseId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @Operation(summary = "Atualizar curso", description = "Endpoint para atualizar um curso existente")
    public Mono<CourseResponse> updateCourse(@PathVariable Long courseId,
                                             @RequestBody CourseRequest courseRequest) {
        courseRequest.setId(courseId);
        return updateCourseUseCase.execute(courseRequest);
    }

    @DeleteMapping("/{courseId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @Operation(summary = "Deletar curso", description = "Endpoint para deletar um curso existente")
    public Mono<Void> deleteCourse(@PathVariable Long courseId) {
        return deleteCourseUseCase.execute(courseId);
    }
}
