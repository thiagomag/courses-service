package br.com.thiagomagdalena.coursesservice.configuration.property;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "commons")
public class CommonsPropertyHolder {

    private Date date;

    @Getter
    @Setter
    public static class Date {
        private String serializer;
        private String[] deserializer;
    }

}