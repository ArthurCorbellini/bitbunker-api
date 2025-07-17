package com.artcorb.bitbunker.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(name = "Asset", description = "Schema to hold Asset information")
@Data
public class AssetDto {

  @Schema(description = "Asset UCID", example = "1")
  @NotNull(message = "UCID cannot be null")
  @Positive(message = "UCID must be a positive number")
  private Long ucid;

  @Schema(description = "Asset category")
  @Valid
  private AssetCategoryDto category;

  @Schema(description = "Asset symbol", example = "BTC")
  @NotEmpty(message = "Symbol can not be a null or empty")
  @Size(max = 10, message = "The length of the asset symbol should be less than 10")
  private String symbol;

  @Schema(description = "Asset name", example = "Bitcoin")
  @NotEmpty(message = "Name can not be a null or empty")
  @Size(max = 30, message = "The length of the asset name should be less than 30")
  private String name;

}
