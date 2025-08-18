package br.com.thiagomagdalena.coursesservice.configuration.serializer;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.r2dbc.postgresql.codec.Json;

import java.io.IOException;

public class JsonR2dbcSerializer extends JsonSerializer<Json> {

    public void serialize(Json value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getFactory();
        JsonParser parser = factory.createParser(value.asString());
        JsonNode jsonNode = mapper.readTree(parser);
        gen.writeObject(jsonNode);
    }

}