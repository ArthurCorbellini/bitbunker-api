package com.artcorb.bitbunker.dtos;

import java.time.LocalDateTime;
import com.artcorb.bitbunker.enums.ResponseError;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(name = "Response", description = "Schema to hold the response information")
@Data
public class ResponseDto {

  @Schema(description = "API path called by client", example = "/path/example")
  private String apiPath;

  @Schema(description = "Time when the request happened")
  private LocalDateTime timestamp;

  @Schema(description = "Schema to hold error detail information")
  private ResponseErrorDto error;

  @Schema(description = "Return data, if any",
      example = "Return data, if any (can be a message or a Json object)")
  private Object data;

  public ResponseDto(String apiPath) {
    this.apiPath = apiPath;
    this.timestamp = LocalDateTime.now();
  }

  public void buildError(ResponseError error, Object detail) {
    this.error = new ResponseErrorDto(error.toString(), error.getDescription(), detail);
  }

  @Data
  @AllArgsConstructor
  private class ResponseErrorDto {

    @Schema(description = "Error code", example = "Descriptive error code")
    private String code;

    @Schema(description = "Error description", example = "A brief description about the error")
    private String message;

    @Schema(description = "Error details, if any",
        example = "Details about the error, if any (can be a Json object)")
    private Object details;

  }

}
