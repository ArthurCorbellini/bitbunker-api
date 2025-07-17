package com.artcorb.bitbunker.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AssetType {

  // @formatter:off
  FIAT("Fiat currency"),
  CRYPTO("Cryptocurrency");
  // @formatter:on

  private String label;

}
