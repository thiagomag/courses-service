package br.com.thiagomagdalena.coursesservice.adapter;

import br.com.thiagomagdalena.coursesservice.utils.JsonUtils;
import io.r2dbc.postgresql.codec.Json;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractAdapter<Source, Destination> implements Adapter<Source, Destination> {

    private final Class<Destination> destinationClass;
    protected final JsonUtils jsonUtils;
    protected ModelMapper modelMapper;

    public AbstractAdapter(Class<Destination> destinationClass,
                           JsonUtils jsonUtils) {
        this.destinationClass = destinationClass;
        this.jsonUtils = jsonUtils;
    }

    protected ModelMapper getModelMapper() {
        if (this.modelMapper == null) {
            this.modelMapper = new ModelMapper();
            this.modelMapper.getConfiguration()
                    .setPropertyCondition(Conditions.isNotNull())
                    .setSkipNullEnabled(true)
                    .setAmbiguityIgnored(true)
                    .setMatchingStrategy(MatchingStrategies.STRICT);
        }

        return this.modelMapper;
    }

    @Override
    public Destination adapt(Source source) {
        return Optional.ofNullable(source)
                .map(sourceObject -> this.getModelMapper().map(sourceObject, destinationClass))
                .orElse(null);
    }

    @Override
    public Destination adapt(Source source, Destination destination) {
        return Optional.ofNullable(source)
                .map(sourceObject -> {
                    this.getModelMapper().map(sourceObject, destination);
                    return destination;
                })
                .orElse(null);
    }

    protected Converter<Json, Map<?, ?>> jsonToMap() {
        return context -> {
            if (context.getSource() == null) {
                return null;
            }
            return jsonUtils.readValueOrNull(context.getSource().asString(), Map.class);
        };
    }

    protected <T> Converter<Json, List<T>> jsonToList(Class<T> clazz) {
        return context -> {
            if (context.getSource() == null) {
                return null;
            }
            return jsonUtils.readValueOrNullForList(context.getSource().asString(), clazz);
        };
    }

    protected Converter<Map<?, ?>, Json> mapToJson() {
        return context -> {
            if (context.getSource() == null) {
                return null;
            }
            return Json.of(jsonUtils.writeValueAsStringOrThrow(context.getSource()));
        };
    }

}
