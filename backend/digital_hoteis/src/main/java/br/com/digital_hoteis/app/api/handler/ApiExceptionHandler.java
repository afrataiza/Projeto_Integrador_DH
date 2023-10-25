package br.com.digital_hoteis.app.api.handler;

import br.com.digital_hoteis.model.exception.CategoryNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<?> clinicaNotFoundExceptionHandler(CategoryNotFoundException exception) {
        return ResponseEntity.badRequest().body(
                """
                {
                    "status" : 400,
                    "message" : "%s"
                }       \s
                """.formatted(exception.getMessage())
        );
    }
}
