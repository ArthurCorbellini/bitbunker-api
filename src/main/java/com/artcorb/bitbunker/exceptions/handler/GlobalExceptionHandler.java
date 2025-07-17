package com.artcorb.bitbunker.exceptions.handler;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
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

    List<String> errors = ex.getBindingResult().getAllErrors().stream()
        .map(ObjectError::getDefaultMessage).collect(Collectors.toList());

    ResponseDto dto = new ResponseDto(getApiPath(webRequest));
    dto.buildError(ResponseError.VALIDATION_ERROR, errors, true);

    return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ResponseDto> handleGlobalException(Exception exception,
      WebRequest webRequest) {
    ResponseDto dto = new ResponseDto(getApiPath(webRequest));

    // Os erros de validação do @RequestParam não são coletados pelo "handleMethodArgumentNotValid";
    // >> Caso de testes: DELETE em /asset, passando um número negativo;
    if (exception instanceof ConstraintViolationException) {
      dto.buildError(ResponseError.VALIDATION_ERROR, Arrays.asList(exception.getMessage()), true);
      return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    // TODO send e-mail to dev team or save the error and its information in database Audit table;

    dto.buildError(ResponseError.INTERNAL_SERVER_ERROR, Arrays.asList(exception.getMessage()),
        false);
    return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ResourceAlreadyExistsException.class)
  public ResponseEntity<ResponseDto> handleResourceAlreadyExistsException(
      ResourceAlreadyExistsException exception, WebRequest webRequest) {

    ResponseDto dto = new ResponseDto(getApiPath(webRequest));
    dto.buildError(ResponseError.ALREADY_EXISTS, Arrays.asList(exception.getMessage()), true);

    return new ResponseEntity<>(dto, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ResponseDto> handleResourceNotFoundException(
      ResourceNotFoundException exception, WebRequest webRequest) {

    ResponseDto dto = new ResponseDto(getApiPath(webRequest));
    dto.buildError(ResponseError.NOT_FOUND, Arrays.asList(exception.getMessage()), true);

    return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
  }
}
