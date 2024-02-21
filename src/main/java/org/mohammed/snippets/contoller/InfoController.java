package org.mohammed.snippets.contoller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/info")
public class InfoController {

    @GetMapping
    public Authentication authentication(Authentication authentication) {
        return authentication;
    }
}
