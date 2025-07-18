package com.artcorb.bitbunker.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionType {

  // @formatter:off
  IN("Transaction in"),
  OUT("Transaction out");
  // @formatter:on

  private String label;

}
