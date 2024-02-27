package org.mohammed.snippets.dto;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link org.mohammed.snippets.entity.Snippet}
 */
public record SnippetUpdateDto(
        @NotNull(message = "Title is required") String title,
        @NotNull(message = "Code is required") String code,
        @NotNull(message = "Language is required") String language) implements Serializable {
}