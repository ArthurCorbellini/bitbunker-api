package com.artcorb.bitbunker.dtos;

import java.time.LocalDateTime;
import java.util.List;
import com.artcorb.bitbunker.enums.ResponseError;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(name = "Response", description = "Schema to hold the response information")
@Data
public class ResponseDto {

  @Schema(description = "Informs whether the request was made successfully", example = "true")
  private boolean success;

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
    this.success = true;
    this.apiPath = apiPath;
    this.timestamp = LocalDateTime.now();
  }

  public void buildError(ResponseError error, List<String> messages, boolean display) {
    this.success = false;
    this.error = new ResponseErrorDto(error.toString(), messages, display);
  }

  @Data
  @AllArgsConstructor
  private class ResponseErrorDto {

    @Schema(description = "Error code", example = "Descriptive error code")
    private String code;

    @Schema(description = "Error messages", example = "A brief description about the errors")
    private List<String> messages;

    @Schema(description = "Display error",
        example = "True if the error can be displayed to the final user")
    private boolean display;
  }

}
