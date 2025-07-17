package com.artcorb.bitbunker.mappers;

import java.util.List;
import java.util.stream.Collectors;
import com.artcorb.bitbunker.dtos.AssetDto;
import com.artcorb.bitbunker.dtos.CreateAssetDto;
import com.artcorb.bitbunker.models.Asset;
import com.artcorb.bitbunker.models.AssetCategory;

public class AssetMapper {

  public static Asset toEntity(CreateAssetDto source) {
    Asset target = new Asset();
    target.setUcid(source.getUcid());
    target.setSymbol(source.getSymbol());
    target.setName(source.getName());

    AssetCategory category = new AssetCategory();
    category.setId(source.getCategoryId());
    target.setCategory(category);

    return target;
  }

  public static AssetDto toDto(Asset source) {
    AssetDto target = new AssetDto();
    target.setUcid(source.getUcid());
    target.setCategory(AssetCategoryMapper.toDto(source.getCategory()));
    target.setSymbol(source.getSymbol());
    target.setName(source.getName());
    return target;
  }

  public static List<AssetDto> toDto(List<Asset> source) {
    return source.stream().map(p -> toDto(p)).collect(Collectors.toList());
  }

}
