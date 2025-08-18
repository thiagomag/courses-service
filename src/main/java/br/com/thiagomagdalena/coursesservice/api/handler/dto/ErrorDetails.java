package br.com.thiagomagdalena.coursesservice.api.handler.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder(builderClassName = "ErrorDetailsBuilder", toBuilder = true)
@JsonDeserialize(builder = ErrorDetails.ErrorDetailsBuilder.class)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ErrorDetails implements Serializable {

    @Builder.Default
    private final String code = null;

    @Builder.Default
    private final String origin = null;

    @Builder.Default
    private final String message = null;

    @Builder.Default
    private final List<Object> details = new ArrayList<>();

    @JsonPOJOBuilder(withPrefix = "")
    public static class ErrorDetailsBuilder {
    }
}
