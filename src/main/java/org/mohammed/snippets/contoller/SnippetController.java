package org.mohammed.snippets.contoller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.mohammed.snippets.dto.SnippetCreateDto;
import org.mohammed.snippets.dto.SnippetUpdateDto;
import org.mohammed.snippets.entity.Snippet;
import org.mohammed.snippets.services.SnippetService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/api/snippets")
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class SnippetController {


    private final SnippetService snippetService;

    public SnippetController(SnippetService snippetService) {
        this.snippetService = snippetService;
    }



    @GetMapping("/all")
    public Page<Snippet> getAllSnippets(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        return snippetService.findPaginated(page, size);
    }

    @GetMapping
    public Page<Snippet> getUserSnippets(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, @AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getClaimAsString("preferred_username");
        return snippetService.findAllByCreatedBy(username, page, size);
    }
    @PostMapping
    public Snippet createSnippet(SnippetCreateDto dto) {
        return snippetService.createSnippet(dto);
    }

    @GetMapping("/{id}")
    public Snippet getSnippet(@PathVariable Long id) throws AccessDeniedException {
        return snippetService.getSnippetById(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Snippet> editSnippet(@PathVariable Long id, @RequestBody @Valid SnippetUpdateDto snippetDetails) {
        Snippet updatedSnippet = snippetService.updateSnippet(id, snippetDetails);
        return ResponseEntity.ok(updatedSnippet);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSnippet(@PathVariable Long id) {
        snippetService.deleteSnippet(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
