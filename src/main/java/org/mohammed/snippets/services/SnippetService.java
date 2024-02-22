package org.mohammed.snippets.services;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.mohammed.snippets.dto.SnippetCreateDto;
import org.mohammed.snippets.dto.SnippetUpdateDto;
import org.mohammed.snippets.entity.Snippet;
import org.mohammed.snippets.exception.SnippetNotFoundException;
import org.mohammed.snippets.repository.SnippetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.nio.file.AccessDeniedException;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class SnippetService {

    private final SnippetRepository snippetRepository;


    public SnippetService(SnippetRepository snippetRepository) {
        this.snippetRepository = snippetRepository;
    }

    public Page<Snippet> findPaginated(int page, int size) {
        return snippetRepository.findAll(PageRequest.of(page - 1, size));
    }

    public Page<Snippet> findAllByCreatedBy(String username, int page, int size) {
        return snippetRepository.findAllByCreatedBy(username, PageRequest.of(page - 1, size));
    }

    public Snippet createSnippet(SnippetCreateDto snippetCreateDto) {
        Snippet snippet = new Snippet();
        snippet.setTitle(snippetCreateDto.title());
        snippet.setCode(snippetCreateDto.code());
        return snippetRepository.save(snippet);
    }

    public Snippet getSnippetById(Long id) throws AccessDeniedException {
        Optional<Snippet> optionalSnippet =  snippetRepository.findById(id);
        if (optionalSnippet.isEmpty()) {
            throw new SnippetNotFoundException(id);
        }
        Jwt principal = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getClaimAsString("preferred_username");
        Snippet snippet = optionalSnippet.get();
        if (!snippet.getCreatedBy().equals(username)) {
            throw new AccessDeniedException("You are not allowed to access this snippet");
        }
        return snippet;
    }

    public Snippet updateSnippet(Long id, SnippetUpdateDto dto) {
        return snippetRepository.findById(id).map(
                snippet -> {
                    snippet.setTitle(dto.title());
                    snippet.setCode(dto.code());
                    return snippetRepository.save(snippet);
                })
                .orElseThrow(() -> new SnippetNotFoundException(id));
    }

    public void deleteSnippet(Long id) {
        if (!snippetRepository.existsById(id)) {
            throw new SnippetNotFoundException(id);
        }
        snippetRepository.deleteById(id);
    }


}
