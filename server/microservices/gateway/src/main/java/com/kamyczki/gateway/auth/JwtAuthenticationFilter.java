package com.kamyczki.gateway.auth;

import com.kamyczki.commons.security.UserDetailsImplDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
class JwtAuthenticationFilter implements GlobalFilter {

    @Value("${kamyczki.auth-service}")
    private String AUTH_SERVICE;

    private final WebClient.Builder webClientBuilder;
    private final SecurityProperties securityProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String requestPath = exchange.getRequest().getURI().getPath();

        if (isExcludedPath(requestPath)) {
            return chain.filter(exchange);
        }

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(7);

        return webClientBuilder.build()
                .post()
                .uri("http://"+ AUTH_SERVICE +"/api/auth/validate-token")
                .bodyValue(token)
                .retrieve()
                .bodyToMono(UserDetailsImplDto.class)
                .flatMap(isValid -> chain.filter(exchange))
                .onErrorResume(e -> {
                    //todo: verify error code
                    if(e instanceof WebClientResponseException exception){
                        exchange.getResponse().setStatusCode(exception.getStatusCode());
                        return exchange.getResponse().setComplete();
                    }
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                });
    }

    private boolean isExcludedPath(String requestPath) {
        return securityProperties.getExcludePaths().stream().anyMatch(excludePath -> requestPath.matches(excludePath.replace("**", ".*")));
    }
}
