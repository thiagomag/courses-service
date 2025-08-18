package br.com.thiagomagdalena.coursesservice.configuration.serializer;

import br.com.thiagomagdalena.coursesservice.enums.EnumSerializable;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class EnumSerializer extends JsonSerializer<Enum<?>> {

    @Override
    public void serialize(Enum value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value != null) {
            if (value instanceof EnumSerializable) {
                gen.writeString(((EnumSerializable) value).getValue());
            } else {
                gen.writeString(value.name());
            }
        } else {
            gen.writeNull();
        }
    }

}