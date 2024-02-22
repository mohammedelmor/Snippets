package org.mohammed.snippets.dto;

import jakarta.validation.constraints.NotNull;

public record ErrorResponse(@NotNull(message = "message cannot be empty") String message) {
}
