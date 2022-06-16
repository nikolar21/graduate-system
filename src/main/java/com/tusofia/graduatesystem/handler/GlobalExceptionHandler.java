package com.tusofia.graduatesystem.handler;

import com.tusofia.graduatesystem.config.ObjectErrorConverter;
import com.tusofia.graduatesystem.exceptions.CommentNotFoundException;
import com.tusofia.graduatesystem.exceptions.MentorNotFoundException;
import com.tusofia.graduatesystem.exceptions.ProjectNotFoundException;
import com.tusofia.graduatesystem.exceptions.UserNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
  public ResponseEntity<List<String>> handleException(MethodArgumentNotValidException e) {
    var errors = e.getBindingResult()
        .getAllErrors()
        .stream()
        .map(objectErrorConverter::convert)
        .toList();
    return ResponseEntity.badRequest().body(errors);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<Object> userNotFound(UserNotFoundException userNotFoundException) {
    return new ResponseEntity<>(userNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MentorNotFoundException.class)
  public ResponseEntity<Object> mentorNotFound(MentorNotFoundException mentorNotFoundException) {
    return new ResponseEntity<>(mentorNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ProjectNotFoundException.class)
  public ResponseEntity<Object> mentorNotFound(ProjectNotFoundException projectNotFoundException) {
    return new ResponseEntity<>(projectNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(CommentNotFoundException.class)
  public ResponseEntity<Object> mentorNotFound(CommentNotFoundException commentNotFoundException) {
    return new ResponseEntity<>(commentNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
  }
}
