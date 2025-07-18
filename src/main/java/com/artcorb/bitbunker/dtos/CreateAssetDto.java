package com.artcorb.bitbunker.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(name = "CreateAsset", description = "Schema to create Asset")
@Data
public class CreateAssetDto {

  @Schema(description = "Asset UCID", example = "1")
  @Positive(message = "UCID must be a positive number")
  private Long ucid;

  @Schema(description = "Asset category ID", example = "1")
  @Positive(message = "Asset category ID must be a positive number")
  @NotNull(message = "Asset category ID cannot be null")
  private Long categoryId;

  @Schema(description = "Asset symbol", example = "BTC")
  @NotEmpty(message = "Symbol cannot be null or empty")
  @Size(max = 10, message = "The length of the asset symbol should be less than 10")
  private String symbol;

  @Schema(description = "Asset name", example = "Bitcoin")
  @NotEmpty(message = "Name cannot be null or empty")
  @Size(max = 30, message = "The length of the asset name should be less than 30")
  private String name;

}
