package org.mohammed.snippets.services;

import lombok.extern.slf4j.Slf4j;
import org.mohammed.snippets.entity.Snippet;
import org.mohammed.snippets.repository.SnippetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SnippetService {

    private final SnippetRepository snippetRepository;

    public SnippetService(SnippetRepository snippetRepository) {
        this.snippetRepository = snippetRepository;
    }

    public Page<Snippet> findPaginated(int page, int size) {
        return snippetRepository.findAll(PageRequest.of(page - 1, size));
    }


}
