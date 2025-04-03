package com.artcorb.bitbunker.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Schema(name = "CreateTransaction", description = "Schema to create Transaction")
@Data
public class CreateTransactionDto {

  @Schema(description = "Asset ID", example = "1")
  @Positive(message = "Asset ID must be a positive number")
  @NotNull(message = "Asset ID cannot be null")
  private Long assetId;

  @Schema(description = "Transaction type", example = "DEPOSIT",
      allowableValues = {"DEPOSIT", "WITHDRAWAL"})
  @NotEmpty(message = "Type can not be a null or empty")
  @Pattern(regexp = "DEPOSIT|WITHDRAWAL",
      message = "Invalid type - Allowed values: DEPOSIT|WITHDRAWAL")
  private String type;

  @Schema(description = "Amount", example = "1234.56")
  @Positive(message = "Amount must be a positive number")
  @NotNull(message = "Amount cannot be null")
  private BigDecimal amount;

  @Schema(description = "Unit price", example = "1234.56")
  @Positive(message = "Unit price must be a positive number")
  @NotNull(message = "Unit price cannot be null")
  private BigDecimal unitPrice;

  @Schema(description = "Total value", example = "1234.56")
  @Positive(message = "Total value must be a positive number")
  @NotNull(message = "Total value cannot be null")
  private BigDecimal totalValue;

  @Schema(description = "Date and time in ISO 8601 format", example = "2024-04-02T14:30:00")
  @NotNull(message = "Date Time cannot be null")
  private LocalDateTime dateTime;

  @Schema(description = "Additional notes about the transaction", example = "Payment received")
  private String notes;

}
