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

  @Schema(description = "Order type", example = "BUY", allowableValues = {"BUY", "SELL"})
  @NotEmpty(message = "Type can not be a null or empty")
  @Pattern(regexp = "BUY|SELL", message = "Invalid type - Allowed values: BUY|SELL")
  private String type;

  @Schema(description = "Order quantity", example = "9999")
  @NotNull(message = "Quantity can not be a null")
  private BigDecimal quantity;

  @Schema(description = "Order fiat currency moved", example = "9999")
  @NotNull(message = "Fiat currency moved can not be a null")
  private BigDecimal fiatCurrencyMoved;

  @Schema(description = "Order notes", example = "Some order note")
  private String notes;
}
