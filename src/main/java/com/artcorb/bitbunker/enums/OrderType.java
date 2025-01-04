package com.artcorb.bitbunker.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderType {

  // @formatter:off
  BUY("Buy"),
  SELL("Sell");
  // @formatter:on

  private String label;

}
