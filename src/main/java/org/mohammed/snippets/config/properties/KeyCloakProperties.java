package org.mohammed.snippets.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("keycloak")
@Getter
@Setter
public class KeyCloakProperties {

    private String keySetURI;

    private final String GROUPS = "groups";
    private final String REALM_ACCESS_CLAIM = "realm_access";
    private final String ROLES_CLAIM = "roles";


}
