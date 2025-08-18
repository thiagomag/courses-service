package br.com.thiagomagdalena.coursesservice.api.dto.course;

import br.com.thiagomagdalena.coursesservice.enums.CourseCategoryEnum;
import br.com.thiagomagdalena.coursesservice.persistance.domain.Module;
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
public class CourseResponse {

    private Long id;
    private String name;
    private String description;
    private CourseCategoryEnum category;
    private Boolean active;
    private List<Module> modules;

    public List<Module> getModules() {
        if (modules == null) {
            return new ArrayList<>();
        }
        return modules;
    }
}
