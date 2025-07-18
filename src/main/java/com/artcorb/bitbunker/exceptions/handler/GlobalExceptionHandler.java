package com.artcorb.bitbunker.exceptions.handler;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import com.artcorb.bitbunker.common.MyResponseBuilder;
import com.artcorb.bitbunker.dtos.ResponseDto;
import com.artcorb.bitbunker.enums.ResponseError;
import com.artcorb.bitbunker.exceptions.ResourceAlreadyExistsException;
import com.artcorb.bitbunker.exceptions.ResourceNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

  private final MyResponseBuilder mrb;

  @ExceptionHandler(ResourceAlreadyExistsException.class)
  public ResponseEntity<ResponseDto> handleResourceAlreadyExistsException(
      ResourceAlreadyExistsException exception) {
    return mrb.conflict(ResponseError.ALREADY_EXISTS, List.of(exception.getMessage()), true);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ResponseDto> handleResourceNotFoundException(
      ResourceNotFoundException exception, WebRequest webRequest) {
    return mrb.notFound(ResponseError.NOT_FOUND, List.of(exception.getMessage()), true);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ResponseDto> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException exception) {
    List<String> errors = exception.getBindingResult().getAllErrors().stream()
        .map(ObjectError::getDefaultMessage).toList();

    return ResponseEntity.badRequest()
        .body(ResponseDto.buildError(ResponseError.VALIDATION_ERROR, errors, true));
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ResponseDto> handleConstraintViolationException(
      ConstraintViolationException exception) {
    return mrb.badRequest(ResponseError.VALIDATION_ERROR, List.of(exception.getMessage()), true);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ResponseDto> handleGlobalException(Exception exception) {
    // TODO send e-mail to dev team or save the error and its information in database Audit table;
    return mrb.internalServerError(ResponseError.INTERNAL_SERVER_ERROR,
        List.of(exception.getMessage()), false);
  }

}
