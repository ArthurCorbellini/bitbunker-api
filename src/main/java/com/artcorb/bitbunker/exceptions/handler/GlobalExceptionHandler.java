package com.artcorb.bitbunker.exceptions.handler;

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
import com.artcorb.bitbunker.dtos.ResponseDto;
import com.artcorb.bitbunker.enums.ResponseError;
import com.artcorb.bitbunker.exceptions.ResourceAlreadyExistsException;
import com.artcorb.bitbunker.exceptions.ResourceNotFoundException;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  private String getApiPath(WebRequest webRequest) {
    return webRequest.getDescription(false).replace("uri=", "");
  }

  /**
   * Handle all jakarta.validation errors
   */
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatusCode status, WebRequest webRequest) {

    Map<String, List<String>> validationErrors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String validationMsg = error.getDefaultMessage();

      validationErrors.computeIfAbsent(fieldName, k -> new ArrayList<>()).add(validationMsg);
    });

    ResponseDto dto = new ResponseDto(getApiPath(webRequest));
    dto.buildError(ResponseError.VALIDATION_ERROR, validationErrors);

    return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ResponseDto> handleGlobalException(Exception exception,
      WebRequest webRequest) {
    ResponseDto dto = new ResponseDto(getApiPath(webRequest));

    // Os erros de validação do @RequestParam não são coletados pelo "handleMethodArgumentNotValid";
    // >> Caso de testes: DELETE em /token, passando um número negativo;
    if (exception instanceof ConstraintViolationException) {
      dto.buildError(ResponseError.VALIDATION_ERROR, exception.getMessage());
      return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    // TODO send e-mail to dev team or save the error and its information in database Audit table;

    dto.buildError(ResponseError.INTERNAL_SERVER_ERROR, exception.getMessage());
    return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ResourceAlreadyExistsException.class)
  public ResponseEntity<ResponseDto> handleResourceAlreadyExistsException(
      ResourceAlreadyExistsException exception, WebRequest webRequest) {

    ResponseDto dto = new ResponseDto(getApiPath(webRequest));
    dto.buildError(ResponseError.ALREADY_EXISTS, exception.getMessage());

    return new ResponseEntity<>(dto, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ResponseDto> handleResourceNotFoundException(
      ResourceNotFoundException exception, WebRequest webRequest) {

    ResponseDto dto = new ResponseDto(getApiPath(webRequest));
    dto.buildError(ResponseError.NOT_FOUND, exception.getMessage());

    return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
  }
}
