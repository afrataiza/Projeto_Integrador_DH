package br.com.digital_hoteis.app.api.handler;

import br.com.digital_hoteis.app.api.dto.response.ProblemResponse;
import br.com.digital_hoteis.model.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionApiHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ProblemResponse> categoryNotFoundExceptionHandler(NotFoundException ex) {
        ProblemResponse problemResponse =
                new ProblemResponse(HttpStatus.NOT_FOUND.value(), ex.getDetail(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemResponse);
    }
}