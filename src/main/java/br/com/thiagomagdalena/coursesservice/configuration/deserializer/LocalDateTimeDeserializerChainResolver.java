package br.com.thiagomagdalena.coursesservice.configuration.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class LocalDateTimeDeserializerChainResolver extends LocalDateTimeDeserializer {

    private static final String LOG_PREFIX = "[LOCAL_DATETIME_DESERIALIZER_CHAIN_RESOLVER]";

    private final List<LocalDateTimeDeserializer> localDateTimeDeserializerChain;

    public LocalDateTimeDeserializerChainResolver(String[] formats) {
        super(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        localDateTimeDeserializerChain = new ArrayList<>();
        for (String format : formats) {
            localDateTimeDeserializerChain.add(new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(format)));
        }
    }

    @Override
    public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) {
        for (LocalDateTimeDeserializer deserializer : localDateTimeDeserializerChain) {
            try {
                if (parser.hasToken(JsonToken.VALUE_NUMBER_INT)) {
                    long value = parser.getValueAsLong();
                    Instant instant = Instant.ofEpochMilli(value);
                    return LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
                }
                return deserializer.deserialize(parser, context);
            } catch (IOException e) {
                log.debug("{} could not deserialize, falling back", LOG_PREFIX);
            }
        }
        throw new DateTimeParseException(String.format("%s Error to deserialize local date time!", LOG_PREFIX), "", 0);
    }

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt, LocalDateTime intoValue) {
        for (LocalDateTimeDeserializer deserializer : localDateTimeDeserializerChain) {
            try {
                return deserializer.deserialize(p, ctxt, intoValue);
            } catch (IOException e) {
                log.debug("{} could not deserialize, falling back", LOG_PREFIX);
            }
        }
        throw new DateTimeParseException(String.format("%s Error to deserialize local date time!", LOG_PREFIX), "", 0);
    }
}
