package org.mohammed.snippets.contoller;

import lombok.extern.slf4j.Slf4j;
import org.mohammed.snippets.entity.Snippet;
import org.mohammed.snippets.services.SnippetService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/snippets")
@Slf4j
public class SnippetController {


    private final SnippetService snippetService;

    public SnippetController(SnippetService snippetService) {
        this.snippetService = snippetService;
    }

    @GetMapping
    public Page<Snippet> getSnippets(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        return snippetService.findPaginated(page, size);
    }


}
