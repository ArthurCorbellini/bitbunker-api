package com.artcorb.bitbunker.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(name = "Token", description = "Schema to hold Token information")
@Data
public class TokenDto {

  @Schema(description = "Token UCID", example = "1")
  @NotEmpty(message = "UCID can not be a null or empty")
  @Size(min = 1, max = 10, message = "The length of the token UCID should be between 1 and 10")
  private long ucid;

  @Schema(description = "Token name", example = "Bitcoin")
  @NotEmpty(message = "Name can not be a null or empty")
  @Size(min = 1, max = 30, message = "The length of the token name should be between 1 and 30")
  private String name;

  @Schema(description = "Token symbol", example = "BTC")
  @NotEmpty(message = "Symbol can not be a null or empty")
  @Size(min = 1, max = 10, message = "The length of the token symbol should be between 1 and 10")
  private String symbol;

  @Schema(description = "Token rank", example = "TIER_S",
      allowableValues = {"TIER_S", "TIER_A", "TIER_B", "TIER_C", "TIER_D", "TIER_E", "NO_TIER"})
  @NotEmpty(message = "Rank can not be a null or empty")
  private String classification;

}
