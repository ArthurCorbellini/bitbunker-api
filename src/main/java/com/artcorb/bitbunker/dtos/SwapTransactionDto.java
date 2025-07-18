package com.artcorb.bitbunker.dtos;

import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Schema(name = "Swap Transaction", description = "Schema to hold swap transactions information")
@Data
public class SwapTransactionDto {

  @Schema(description = "Asset ID", example = "1")
  @Positive(message = "Asset ID must be a positive number")
  @NotNull(message = "Asset ID cannot be null")
  private Long assetId;

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

}
