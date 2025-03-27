package com.artcorb.bitbunker.dtos;

import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "Buy and Sell Transactions",
    description = "Schema to hold buy and sell transactions information")
@Data
public class BuyAndSellTransactionDto {

  private Long assetId;

  private BigDecimal amount;

  private BigDecimal unitPrice;

  private BigDecimal totalValue;

}
