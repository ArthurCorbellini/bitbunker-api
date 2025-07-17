package com.artcorb.bitbunker.dtos;

import java.time.LocalDateTime;
import java.util.List;
import com.artcorb.bitbunker.enums.ResponseError;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "Response", description = "Schema to hold the response information")
@Data
public class ResponseDto {

  @Schema(description = "Informs whether the request was made successfully", example = "true")
  private boolean success;

  @Schema(description = "Time when the request happened")
  private LocalDateTime timestamp;

  @Schema(description = "Return data, if any",
      example = "Return data, if any (can be a message or a Json object)")
  private Object data;

  @Schema(description = "Return error, if any")
  private ResponseErrorDto error;

  private ResponseDto(boolean success) {
    this.success = success;
    this.timestamp = LocalDateTime.now();
  }

  public static ResponseDto buildSuccess(Object data) {
    ResponseDto r = new ResponseDto(true);
    r.setData(data);
    return r;
  }

  public static ResponseDto buildError(ResponseError error, List<String> messages,
      boolean display) {
    ResponseDto r = new ResponseDto(false);
    r.setError(new ResponseErrorDto(error.toString(), messages, display));
    return r;
  }

}
