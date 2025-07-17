package com.artcorb.bitbunker.dtos;

import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(name = "CreateAsset", description = "Schema to create Asset")
@Data
public class CreateAssetCategoryDto {

  @Schema(description = "Asset category name", example = "Altcoin A")
  @NotEmpty(message = "Name can not be a null or empty")
  @Size(max = 30, message = "The length of the asset category name should be less than 30")
  private String name;

  @Schema(description = "Recommended percentage as a decimal (e.g., 0.05 for 5%)", example = "0.05")
  @NotNull(message = "Recommended percentage cannot be null")
  private BigDecimal recommendedPercentage;

}
