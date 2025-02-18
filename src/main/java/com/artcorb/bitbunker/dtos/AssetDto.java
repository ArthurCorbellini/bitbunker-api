package com.artcorb.bitbunker.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(name = "Asset", description = "Schema to hold Asset information")
@Data
public class AssetDto {

  @Schema(description = "Asset ID", example = "1")
  private Long id;

  @Schema(description = "Asset UCID", example = "1")
  @Positive(message = "UCID must be a positive number")
  private Long ucid;

  @Schema(description = "Asset name", example = "Bitcoin")
  @NotEmpty(message = "Name can not be a null or empty")
  @Size(max = 30, message = "The length of the asset name should be less than 30")
  private String name;

  @Schema(description = "Asset symbol", example = "BTC")
  @NotEmpty(message = "Symbol can not be a null or empty")
  @Size(max = 10, message = "The length of the asset symbol should be less than 10")
  private String symbol;

  @Schema(description = "Asset type", example = "CRYPTO", allowableValues = {"FIAT", "CRYPTO"})
  @NotEmpty(message = "Type can not be a null or empty")
  @Pattern(regexp = "FIAT|CRYPTO", message = "Invalid classification - Allowed values: FIAT|CRYPTO")
  private String type;

  @Schema(description = "Asset rank", example = "TIER_S",
      allowableValues = {"TIER_S", "TIER_A", "TIER_B", "TIER_C", "TIER_D", "TIER_E", "NO_TIER"})
  @NotEmpty(message = "Classification can not be a null or empty")
  @Pattern(regexp = "TIER_S|TIER_A|TIER_B|TIER_C|TIER_D|TIER_E|NO_TIER",
      message = "Invalid classification - Allowed values: TIER_S|TIER_A|TIER_B|TIER_C|TIER_D|TIER_E|NO_TIER")
  private String classification;

}
