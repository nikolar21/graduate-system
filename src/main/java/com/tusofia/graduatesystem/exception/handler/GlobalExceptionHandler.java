package com.tusofia.graduatesystem.exception.handler;

import com.tusofia.graduatesystem.config.ObjectErrorConverter;
import com.tusofia.graduatesystem.exception.EntityNotFoundException;
import com.tusofia.graduatesystem.model.dto.error.ErrorDto;
import com.tusofia.graduatesystem.model.dto.error.ValidationErrorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

  private final ObjectErrorConverter objectErrorConverter;

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ValidationErrorDto> handleException(MethodArgumentNotValidException e) {
    var errors = e.getBindingResult()
        .getAllErrors()
        .stream()
        .map(objectErrorConverter::convert)
        .toList();
    ValidationErrorDto validationErrorDto = new ValidationErrorDto(HttpStatus.BAD_REQUEST, errors);
    return new ResponseEntity<>(validationErrorDto, new HttpHeaders(), validationErrorDto.getStatus());
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
    ErrorDto errorDto = new ErrorDto(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());

    return new ResponseEntity<>(errorDto, new HttpHeaders(), errorDto.getStatus());
  }
}
