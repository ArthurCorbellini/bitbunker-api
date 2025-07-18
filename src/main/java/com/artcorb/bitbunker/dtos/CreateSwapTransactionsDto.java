package com.artcorb.bitbunker.dtos;

import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(name = "Create Swap Transactions", description = "Schema to create swap transactions")
@Data
public class CreateSwapTransactionsDto {

  @Schema(description = "Date and time in ISO 8601 format", example = "2024-04-02T14:30:00")
  @NotNull(message = "Date Time cannot be null")
  private LocalDateTime dateTime;

  @Schema(description = "Transaction notes, if any", example = "Some transaction note")
  private String notes;

  @Schema(description = "Transaction swap IN")
  @Valid
  private SwapTransactionDto swapIn;

  @Schema(description = "Transaction swap OUT")
  @Valid
  private SwapTransactionDto swapOut;

}
