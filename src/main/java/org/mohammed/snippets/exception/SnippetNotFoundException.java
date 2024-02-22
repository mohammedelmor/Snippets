package org.mohammed.snippets.exception;

public class SnippetNotFoundException extends RuntimeException {
    public SnippetNotFoundException(long id) {
        super("Snippet not found with id: " + id);
    }
}
