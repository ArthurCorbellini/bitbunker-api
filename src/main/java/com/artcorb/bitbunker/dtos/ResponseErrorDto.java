package com.artcorb.bitbunker.dtos;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseErrorDto {

  @Schema(description = "Error code", example = "Descriptive error code")
  private String code;

  @Schema(description = "Error messages", example = "A brief description about the errors")
  private List<String> messages;

  @Schema(description = "Display error",
      example = "True if the error can be displayed to the final user")
  private boolean display;

}
