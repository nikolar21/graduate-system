package com.tusofia.graduatesystem.handler;

import com.tusofia.graduatesystem.config.ObjectErrorConverter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

  private final ObjectErrorConverter objectErrorConverter;

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<String>> handleException(MethodArgumentNotValidException e) {
    var errors = e.getBindingResult()
        .getAllErrors()
        .stream()
        .map(objectErrorConverter::convert)
        .toList();
    return ResponseEntity.badRequest().body(errors);
  }
}
