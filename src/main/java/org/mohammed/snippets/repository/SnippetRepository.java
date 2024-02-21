package org.mohammed.snippets.repository;

import org.mohammed.snippets.entity.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(exported = false)
public interface SnippetRepository extends JpaRepository<Snippet, Long> {

}