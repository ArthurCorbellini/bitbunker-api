package com.artcorb.bitbunker.common;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.artcorb.bitbunker.dtos.ResponseDto;
import com.artcorb.bitbunker.enums.ResponseError;

@Component
public class MyResponseBuilder {

  protected static final String MESSAGE_200 = "Request processed successfully";

  public ResponseEntity<ResponseDto> ok() {
    return ResponseEntity.ok().body(ResponseDto.buildSuccess(MESSAGE_200));
  }

  public ResponseEntity<ResponseDto> ok(Object data) {
    return ResponseEntity.ok().body(ResponseDto.buildSuccess(data));
  }

  public ResponseEntity<ResponseDto> created(Object data) {
    return ResponseEntity.status(HttpStatus.CREATED).body(ResponseDto.buildSuccess(data));
  }

  public ResponseEntity<ResponseDto> notFound(ResponseError error, List<String> messages,
      boolean display) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(ResponseDto.buildError(error, messages, display));
  }

  public ResponseEntity<ResponseDto> badRequest(ResponseError error, List<String> messages,
      boolean display) {
    return ResponseEntity.badRequest().body(ResponseDto.buildError(error, messages, display));
  }

  public ResponseEntity<ResponseDto> conflict(ResponseError error, List<String> messages,
      boolean display) {
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(ResponseDto.buildError(error, messages, display));
  }

  public ResponseEntity<ResponseDto> internalServerError(ResponseError error, List<String> messages,
      boolean display) {
    return ResponseEntity.internalServerError()
        .body(ResponseDto.buildError(error, messages, display));
  }

}
