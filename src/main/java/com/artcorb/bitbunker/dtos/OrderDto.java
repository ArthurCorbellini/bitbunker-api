package com.artcorb.bitbunker.dtos;

import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(name = "Order", description = "Schema to hold Order information")
@Data
public class OrderDto {

  @Schema(description = "Order ID", example = "1")
  private Long id;

  @Schema(description = "Order asset")
  @Valid
  private AssetDto asset;

  @Schema(description = "Order type", example = "BUY", allowableValues = {"DEPOSIT", "BUY", "SELL"})
  @NotEmpty(message = "Type can not be a null or empty")
  @Pattern(regexp = "DEPOSIT|BUY|SELL", message = "Invalid type - Allowed values: DEPOSIT|BUY|SELL")
  private String type;

  @Schema(description = "Asset quantity", example = "9999")
  @NotNull(message = "Quantity can not be a null")
  private BigDecimal quantity;

  @Schema(description = "BRL quantity", example = "9999")
  @NotNull(message = "BRL quantity can not be a null")
  private BigDecimal brlQuantity;

  @Schema(description = "Order notes", example = "Some order note")
  private String notes;
}
