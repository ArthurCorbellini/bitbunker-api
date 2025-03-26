package com.artcorb.bitbunker.dtos;

import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(name = "Transaction", description = "Schema to hold Transaction information")
@Data
public class TransactionDto {

  @Schema(description = "Transaction ID", example = "1")
  private Long id;

  @Schema(description = "Transaction asset")
  @Valid
  private AssetDto asset;

  @Schema(description = "Transaction type", example = "BUY",
      allowableValues = {"DEPOSIT", "BUY", "SELL"})
  @NotEmpty(message = "Type can not be a null or empty")
  @Pattern(regexp = "DEPOSIT|BUY|SELL", message = "Invalid type - Allowed values: DEPOSIT|BUY|SELL")
  private String type;

  @Schema(description = "Asset quantity", example = "9999")
  @NotNull(message = "Quantity can not be a null")
  private BigDecimal quantity;

  @Schema(description = "BRL quantity", example = "9999")
  @NotNull(message = "BRL quantity can not be a null")
  private BigDecimal brlQuantity;

  @Schema(description = "Transaction notes", example = "Some transaction note")
  private String notes;
}
