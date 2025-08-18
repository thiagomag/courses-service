package br.com.thiagomagdalena.coursesservice.api.dto.module;

import br.com.thiagomagdalena.coursesservice.persistance.domain.Lesson;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ModuleResponse {

    private Long id;
    private String title;
    private String description;
    private Integer position;
    private Long courseId;
    private List<Lesson> lessons;

    public List<Lesson> getLessons() {
        if (lessons == null) {
            return new ArrayList<>();
        }
        return lessons;
    }
}
