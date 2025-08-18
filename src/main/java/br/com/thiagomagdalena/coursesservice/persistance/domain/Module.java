package br.com.thiagomagdalena.coursesservice.persistance.domain;

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
@Table(Module.TABLE_NAME)
public class Module extends BaseEntity<Long> {

    public static final String TABLE_NAME = "modules";

    @Id
    private Long id;
    private String title;
    private String description;
    private Integer position;
    private Long courseId;
    @Transient
    private List<Lesson> lessons;
}
