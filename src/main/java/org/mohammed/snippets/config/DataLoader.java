package org.mohammed.snippets.config;

import lombok.extern.slf4j.Slf4j;
import org.mohammed.snippets.entity.Snippet;
import org.mohammed.snippets.repository.SnippetRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@Profile("testdata")
public class DataLoader {

    private final SnippetRepository snippetRepository;

    public DataLoader(SnippetRepository snippetRepository) {
        this.snippetRepository = snippetRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        log.info("Loading data");

        snippetRepository.deleteAll();

        var snippet1 = new Snippet();
        snippet1.setTitle("JS function to add two numbers");
        snippet1.setCode("""
                function add(a, b) {
                    return a + b;
                }
                """);
        snippet1.setLanguage("javascript");
        var snippet2 = new Snippet();
        snippet2.setTitle("JS function to subtract numbers");
        snippet2.setCode("""
                function subtract(a, b) {
                    return a - b;
                }
                """);
        snippet2.setLanguage("javascript");

        var snippet3 = new Snippet();
        snippet3.setTitle("JS function to multiply numbers");
        snippet3.setCode("""
                function multiply(a, b) {
                    return a * b;
                }
                """);
        snippet3.setLanguage("javascript");
        var snippet4 = new Snippet();
        snippet4.setTitle("JS function to power numbers");
        snippet4.setCode("""
                function power(a, b) {
                    return a ** b;
                }
                """);
        snippet4.setLanguage("javascript");
        snippetRepository.saveAll(List.of(snippet1, snippet2, snippet3, snippet4));
    }

}
