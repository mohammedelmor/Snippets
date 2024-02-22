package org.mohammed.snippets.contoller;

import org.mohammed.snippets.dto.ErrorResponse;
import org.mohammed.snippets.exception.SnippetNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = SnippetController.class)
public class SnippetExceptionHandler {

    @ExceptionHandler(SnippetNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSnippetNotFoundException(SnippetNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
    }

}
