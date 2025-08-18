package br.com.thiagomagdalena.coursesservice.configuration.deserializer.enums;

import br.com.thiagomagdalena.coursesservice.configuration.deserializer.exceptions.EnumValidationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public abstract class AbstractEnumDeserializer<T> extends JsonDeserializer<T> {

    @Override
    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser == null) {
            return null;
        }

        final var jsonName = jsonParser.getParsingContext().getCurrentName();
        final var jsonValue = jsonParser.getValueAsString();
        try {
            final var value = convertToEnum(jsonValue);
            if (value == null) {
                throwException(jsonName, jsonValue);
            }

            return value;
        } catch (Exception e) {
            return throwException(jsonName, jsonValue);
        }
    }

    private T throwException(String jsonName, String jsonValue) {
        final var msg = String.format("Could not deserialize value %s in field %s", jsonValue, jsonName);
        log.error("{} {}", getLogPrefix(), msg);
        throw new EnumValidationException(jsonName, jsonValue, msg);
    }

    protected abstract T convertToEnum(String value);
    protected abstract String getLogPrefix();

}