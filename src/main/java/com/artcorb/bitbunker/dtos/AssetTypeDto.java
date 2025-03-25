package com.artcorb.bitbunker.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "AssetType", description = "Schema to hold Asset Types")
@Data
public class AssetTypeDto {
  private final String id;
  private final String label;
}
