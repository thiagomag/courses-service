package br.com.thiagomagdalena.coursesservice.persistance.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder(toBuilder = true)
@Table(Lesson.TABLE_NAME)
public class Lesson extends BaseEntity<Long> {

    public static final String TABLE_NAME = "lessons";

    @Id
    private Long id;
    private String title;
    private String description;
    private String videoUrl;
    private Integer durationInMinutes;
    private Long moduleId;
}