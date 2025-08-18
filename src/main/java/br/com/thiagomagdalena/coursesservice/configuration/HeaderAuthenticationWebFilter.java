package br.com.thiagomagdalena.coursesservice.configuration;

import br.com.thiagomagdalena.coursesservice.configuration.property.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Component
public class HeaderAuthenticationWebFilter implements WebFilter {

    private static final String ROLES_HEADER = "X-User-Roles";
    private final List<PathPattern> publicPatterns;
    private final List<PathPattern> postPublicPatterns;

    public HeaderAuthenticationWebFilter(SecurityProperties securityProperties) {
        PathPatternParser parser = new PathPatternParser();
        this.publicPatterns = securityProperties.getPublicPaths().stream()
                .map(parser::parse)
                .toList();
        this.postPublicPatterns = securityProperties.getPostPublicPaths().stream()
                .map(parser::parse)
                .toList();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        if (isPublicPath(request)) {
            log.info("Rota pública acessada: {}. Acesso permitido sem autenticação.", request.getURI());
            return chain.filter(exchange);
        }

        String rolesHeader = request.getHeaders().getFirst(ROLES_HEADER);
        if (rolesHeader == null || rolesHeader.isEmpty()) {
            log.info("Header '{}' não encontrado para uma rota protegida: {}. Acesso será negado.", ROLES_HEADER, request.getURI());
            return chain.filter(exchange);
        }

        List<SimpleGrantedAuthority> authorities = Stream.of(rolesHeader.split(","))
                .map(String::trim)
                .map(SimpleGrantedAuthority::new)
                .toList();

        var authentication = new UsernamePasswordAuthenticationToken(
                "pre-authenticated-user", null, authorities);

        return chain.filter(exchange)
                .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
    }

    private boolean isPublicPath(ServerHttpRequest request) {
        if (request.getMethod().name().equalsIgnoreCase("POST")) {
            return postPublicPatterns.stream().anyMatch(pattern -> pattern.matches(request.getPath()));
        }
        return publicPatterns.stream().anyMatch(pattern -> pattern.matches(request.getPath()));
    }
}
