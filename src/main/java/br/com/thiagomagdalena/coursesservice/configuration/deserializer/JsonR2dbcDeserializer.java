package br.com.thiagomagdalena.coursesservice.configuration.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.r2dbc.postgresql.codec.Json;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class JsonR2dbcDeserializer extends JsonDeserializer<Json> {

    @Override
    public Json deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser == null) {
            return null;
        }

        final var json = jsonParser.readValueAsTree().toString();
        return StringUtils.isBlank(json) ? null : Json.of(json);
    }

}