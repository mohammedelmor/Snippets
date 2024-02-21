package org.mohammed.snippets.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class JwtAuthenticationConverter implements Converter<Jwt, CustomAuthentication> {

    @Override
    public CustomAuthentication convert(Jwt source) {
        Map<String, Object> realmAccess = source.getClaimAsMap("realm_access");
        List<SimpleGrantedAuthority> authorities = realmAccess.get("roles") != null ?
                ((List<String>) realmAccess.get("roles")).stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                        .toList() : List.of();

        return new CustomAuthentication(source, authorities);
    }
}
