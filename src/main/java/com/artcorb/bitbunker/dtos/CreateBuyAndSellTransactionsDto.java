package com.artcorb.bitbunker.dtos;

import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "Create Buy and Sell Transactions",
    description = "Schema to create buy and sell transactions information")
@Data
public class CreateBuyAndSellTransactionsDto {

  private LocalDateTime date;

  private String notes;

  private BuyAndSellTransactionDto buy;

  private BuyAndSellTransactionDto sell;

}
