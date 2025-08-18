package br.com.thiagomagdalena.coursesservice.api.dto.module;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ModulesRequestParams {

    private String title;
    private Set<Long> ids;
    private Long courseId;

    public Set<Long> getIds() {
        if (ids == null) {
            ids = new HashSet<>();
        }
        return ids;
    }

    @JsonIgnore
    public void addIds(String idsStr) {
        if (StringUtils.isNotBlank(idsStr)) {
            getIds().addAll(
                    Stream.of(idsStr.split("[ ]*[,][ ]*"))
                            .map(Long::valueOf)
                            .collect(Collectors.toSet())
            );
        }
    }

    @JsonIgnore
    public Set<String> getIdsAsString() {
        return getIds().stream()
                .map(String::valueOf)
                .collect(Collectors.toSet());
    }
}
