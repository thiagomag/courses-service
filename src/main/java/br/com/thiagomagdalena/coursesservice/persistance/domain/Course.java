package br.com.thiagomagdalena.coursesservice.persistance.domain;

import br.com.thiagomagdalena.coursesservice.enums.CourseCategoryEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder(toBuilder = true)
@Table(Course.TABLE_NAME)
public class Course extends BaseEntity<Long> {

    public static final String TABLE_NAME = "courses";

    @Id
    private Long id;
    private String name;
    private String description;
    private CourseCategoryEnum category;
    private Boolean active;
    @Transient
    private List<Module> modules;

}
