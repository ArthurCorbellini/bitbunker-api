package com.artcorb.bitbunker.mappers;

import java.util.List;
import java.util.stream.Collectors;
import com.artcorb.bitbunker.dtos.AssetCategoryDto;
import com.artcorb.bitbunker.dtos.CreateAssetCategoryDto;
import com.artcorb.bitbunker.models.AssetCategory;

public class AssetCategoryMapper {

  public static AssetCategory toEntity(CreateAssetCategoryDto source) {
    AssetCategory target = new AssetCategory();
    target.setName(source.getName());
    target.setRecommendedPercentage(source.getRecommendedPercentage());
    return target;
  }

  public static AssetCategoryDto toDto(AssetCategory source) {
    AssetCategoryDto target = new AssetCategoryDto();
    target.setId(source.getId());
    target.setName(source.getName());
    target.setRecommendedPercentage(source.getRecommendedPercentage());
    return target;
  }

  public static List<AssetCategoryDto> toDto(List<AssetCategory> source) {
    return source.stream().map(p -> toDto(p)).collect(Collectors.toList());
  }

}
