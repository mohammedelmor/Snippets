package org.mohammed.snippets.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.mohammed.snippets.config.properties.KeyCloakProperties;
import org.mohammed.snippets.security.JwtAuthenticationConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@Slf4j
public class SecurityConfig {

    private final KeyCloakProperties keyCloakProperties;
    private final JwtAuthenticationConverter jwtAuthenticationConverter;


    public SecurityConfig(KeyCloakProperties keyCloakProperties, JwtAuthenticationConverter jwtAuthenticationConverter) {
        this.keyCloakProperties = keyCloakProperties;
        this.jwtAuthenticationConverter = jwtAuthenticationConverter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.oauth2ResourceServer(
                c -> c.jwt(
                        j -> j.jwkSetUri(this.keyCloakProperties.getKeySetURI())
                                .jwtAuthenticationConverter(jwtAuthenticationConverter)
                ));

        http.authorizeRequests(
                c -> c.requestMatchers("/swagger-ui/**", "/swagger-resources/**", "/swagger-ui.html", "/api-docs/**", "/webjars/**", "/swagger-config/**", "/v3/api-docs/swagger-config", "/v3/api-docs").permitAll()
                        .anyRequest().authenticated()
        );

        return http.build();
    }





}
