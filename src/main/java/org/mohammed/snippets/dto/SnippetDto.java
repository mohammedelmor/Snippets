package org.mohammed.snippets.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link org.mohammed.snippets.entity.Snippet}
 */
@Value
public class SnippetDto implements Serializable {
    @NotNull(message = "Title is required")
    String title;
    @NotNull(message = "Code is required")
    String code;
}