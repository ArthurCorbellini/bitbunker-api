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

  @Schema(description = "Transaction type", example = "OUT", allowableValues = {"IN", "OUT"})
  @NotEmpty(message = "Type cannot be null or empty")
  @Pattern(regexp = "IN|OUT", message = "Invalid type - Allowed values: IN|OUT")
  private String type;

  @Schema(description = "Date and time in ISO 8601 format", example = "2024-04-02T14:30:00")
  @NotNull(message = "Date Time cannot be null")
  private LocalDateTime dateTime;

  @Schema(description = "Quantity", example = "1234.56")
  @Positive(message = "Quantity must be a positive number")
  @NotNull(message = "Quantity cannot be null")
  private BigDecimal quantity;

  @Schema(description = "Unit price", example = "1234.56")
  @Positive(message = "Unit price must be a positive number")
  @NotNull(message = "Unit price cannot be null")
  private BigDecimal unitPrice;

  @Schema(description = "Fee value, if any", example = "1234.56")
  @Positive(message = "Fee must be a positive number")
  private BigDecimal fee;

  @Schema(description = "Transaction notes, if any", example = "Some transaction note")
  private String notes;

}
