package br.com.thiagomagdalena.coursesservice.configuration;

import br.com.thiagomagdalena.coursesservice.configuration.property.SecurityProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final HeaderAuthenticationWebFilter headerAuthenticationWebFilter;
    private final SecurityProperties securityProperties;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        final var publicPaths = securityProperties.getPublicPaths().toArray(new String[0]);
        final var postPublicPaths = securityProperties.getPostPublicPaths().toArray(new String[0]);

        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .addFilterAt(headerAuthenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers(HttpMethod.POST, postPublicPaths).permitAll()
                        .pathMatchers(publicPaths).permitAll()
                        .anyExchange().authenticated()
                )
                .build();
    }
}
