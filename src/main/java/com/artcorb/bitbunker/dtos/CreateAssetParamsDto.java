package com.artcorb.bitbunker.dtos;

import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class CreateAssetParamsDto {
  private List<Map<String, String>> typeOptions;
  private List<Map<String, String>> classificationOptions;
}
