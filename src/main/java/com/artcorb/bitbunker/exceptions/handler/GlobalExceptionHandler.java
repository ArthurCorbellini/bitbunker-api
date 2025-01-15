package com.artcorb.bitbunker.exceptions.handler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.artcorb.bitbunker.dtos.ResponseErrorDto;
import com.artcorb.bitbunker.exceptions.ResourceAlreadyExistsException;
import com.artcorb.bitbunker.exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * Handle all jakarta.validation errors
   */
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatusCode status, WebRequest request) {

    Map<String, List<String>> validationErrors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String validationMsg = error.getDefaultMessage();

      validationErrors.computeIfAbsent(fieldName, k -> new ArrayList<>()).add(validationMsg);
    });

    return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ResponseErrorDto> handleGlobalException(Exception exception,
      WebRequest webRequest) {

    // TODO send e-mail to dev team or save the error and its information in database Audit table;

    ResponseErrorDto dto = new ResponseErrorDto(webRequest.getDescription(false),
        HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), LocalDateTime.now());

    return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ResourceAlreadyExistsException.class)
  public ResponseEntity<ResponseErrorDto> handleResourceAlreadyExistsException(
      ResourceAlreadyExistsException exception, WebRequest webRequest) {
    ResponseErrorDto dto = new ResponseErrorDto(webRequest.getDescription(false),
        HttpStatus.BAD_REQUEST, exception.getMessage(), LocalDateTime.now());
    return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ResponseErrorDto> handleResourceNotFoundException(
      ResourceNotFoundException exception, WebRequest webRequest) {
    ResponseErrorDto dto = new ResponseErrorDto(webRequest.getDescription(false),
        HttpStatus.NOT_FOUND, exception.getMessage(), LocalDateTime.now());
    return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
  }
}
