package com.artcorb.bitbunker.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TokenRank {

  // @formatter:off
  TIER_S("Tier S"),
  TIER_A("Tier A"),
  TIER_B("Tier B"), 
  TIER_C("Tier C"), 
  TIER_D("Tier D"),
  TIER_E("Tier E"),
  NO_TIER("No tier");
  // @formatter:on

  private String label;

}
