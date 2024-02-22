package org.mohammed.snippets.repository;

import org.mohammed.snippets.entity.Snippet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource(exported = false)
public interface SnippetRepository extends JpaRepository<Snippet, Long> {

    Page<Snippet> findAllByCreatedBy(String username, Pageable pageable);
}