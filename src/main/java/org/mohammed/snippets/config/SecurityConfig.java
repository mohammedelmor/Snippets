package org.mohammed.snippets.config;

import lombok.extern.slf4j.Slf4j;
import org.mohammed.snippets.config.properties.KeyCloakProperties;
import org.mohammed.snippets.security.JwtAuthenticationConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.web.SecurityFilterChain;

import java.util.*;
import java.util.stream.Collectors;

import static org.hibernate.validator.internal.metadata.core.ConstraintHelper.GROUPS;

@Configuration
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
                c -> c.anyRequest().authenticated()
        );

        return http.build();
    }


}
