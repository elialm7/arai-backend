package org.arai.Exceptions;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    @Nullable
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            org.springframework.http.HttpHeaders headers, HttpStatusCode status, WebRequest request) {
                
                Map<String, Object> body = new LinkedHashMap<>();
                body.put("timestamp", System.currentTimeMillis());
                body.put("status", status.value());
                List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
                body.put("errors", errors);
                return new ResponseEntity<>(body, status);
    }
}
