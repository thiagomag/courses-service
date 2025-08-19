package br.com.thiagomagdalena.coursesservice.api.controller;

import br.com.thiagomagdalena.coursesservice.api.dto.module.ModuleRequest;
import br.com.thiagomagdalena.coursesservice.api.dto.module.ModuleResponse;
import br.com.thiagomagdalena.coursesservice.api.dto.module.ModulesRequestParams;
import br.com.thiagomagdalena.coursesservice.usecases.module.CreateModuleUseCase;
import br.com.thiagomagdalena.coursesservice.usecases.module.DeleteModuleUseCase;
import br.com.thiagomagdalena.coursesservice.usecases.module.GetAllModulesUseCase;
import br.com.thiagomagdalena.coursesservice.usecases.module.GetModuleUseCase;
import br.com.thiagomagdalena.coursesservice.usecases.module.UpdateModuleUseCase;
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
@RequestMapping("/modules")
@Tag(name = "Módulos",description = "Api de gerenciamento de módulos")
public class ModuleController {

    private final CreateModuleUseCase createModuleUseCase;
    private final GetAllModulesUseCase getAllModulesUseCase;
    private final GetModuleUseCase getModuleUseCase;
    private final UpdateModuleUseCase updateModuleUseCase;
    private final DeleteModuleUseCase deleteModuleUseCase;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @Operation(summary = "Criar módulo", description = "Endpoint para criar um novo módulo")
    public Mono<ModuleResponse> createModule(@Valid @RequestBody ModuleRequest moduleRequest) {
        return createModuleUseCase.execute(moduleRequest);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUBSCRIBER')")
    @Operation(summary = "Listar Módulos", description = "Endpoint para listar todos os módulos")
    public Flux<ModuleResponse> fetchAll(@RequestParam(value = "id__in", required = false) String ids,
                                         @RequestParam(value = "title", required = false) String title,
                                         @RequestParam(value = "course_id", required = false) Long courseId,
                                         @RequestParam(value = "instructor_id", required = false) Long instructorId) {
        final var params = ModulesRequestParams.builder()
                .title(title)
                .courseId(courseId)
                .instructorId(instructorId)
                .build();
        params.addIds(ids);
        return getAllModulesUseCase.execute(params);
    }

    @GetMapping("/{moduleId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUBSCRIBER')")
    @Operation(summary = "Obter módulo por ID", description = "Endpoint para obter um módulo pelo seu ID")
    public Mono<ModuleResponse> fetchById(@PathVariable Long moduleId) {
        return getModuleUseCase.execute(moduleId);
    }

    @PatchMapping("/{moduleId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @Operation(summary = "Atualizar Módulo", description = "Endpoint para atualizar um módulo existente")
    public Mono<ModuleResponse> updateModule(@PathVariable Long moduleId,
                                             @RequestBody ModuleRequest moduleRequest) {
        moduleRequest.setId(moduleId);
        return updateModuleUseCase.execute(moduleRequest);
    }

    @DeleteMapping("/{moduleId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @Operation(summary = "Deletar módulo", description = "Endpoint para deletar um módulo existente")
    public Mono<Void> deleteModule(@PathVariable Long moduleId) {
        return deleteModuleUseCase.execute(moduleId);
    }
}
