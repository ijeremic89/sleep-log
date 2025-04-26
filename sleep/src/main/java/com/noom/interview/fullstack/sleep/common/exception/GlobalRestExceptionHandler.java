package com.noom.interview.fullstack.sleep.common.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.noom.interview.fullstack.sleep.log.model.Mood;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalRestExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            (error) -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });

    ErrorResponse response = new ErrorResponse("Validation failed", errors, LocalDateTime.now());
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex) {
    var response = new ErrorResponse(ex.getMessage(), null, LocalDateTime.now());
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(SleepLogAlreadyExistsException.class)
  public ResponseEntity<Object> handleAlreadyExists(SleepLogAlreadyExistsException ex) {
    var response = new ErrorResponse(ex.getMessage(), null, LocalDateTime.now());
    return new ResponseEntity<>(response, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Object> handleInvalidEnum(HttpMessageNotReadableException ex) {
    if (ex.getCause() instanceof InvalidFormatException formatEx
        && formatEx.getTargetType() == Mood.class) {

      var response =
          new ErrorResponse(
              "Invalid mood. Must be one of: BAD, OK, GOOD.", null, LocalDateTime.now());
      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(
        new ErrorResponse("Malformed JSON", null, LocalDateTime.now()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidDaysException.class)
  public ResponseEntity<Object> handleInvalidDays(InvalidDaysException ex) {
    var response = new ErrorResponse(ex.getMessage(), null, LocalDateTime.now());
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }
}
