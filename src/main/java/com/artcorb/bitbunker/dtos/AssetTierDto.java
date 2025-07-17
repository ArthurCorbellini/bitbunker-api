package com.artcorb.bitbunker.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "AssetTier", description = "Schema to hold Asset Tiers")
@Data
public class AssetTierDto {
  private final String id;
  private final String label;
}
