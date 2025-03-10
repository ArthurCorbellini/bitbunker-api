package com.artcorb.bitbunker.mappers;

import java.util.List;
import java.util.stream.Collectors;
import com.artcorb.bitbunker.dtos.AssetDto;
import com.artcorb.bitbunker.dtos.CreateAssetDto;
import com.artcorb.bitbunker.enums.AssetRank;
import com.artcorb.bitbunker.enums.AssetType;
import com.artcorb.bitbunker.models.Asset;

public class AssetMapper {

  public static Asset toEntity(CreateAssetDto source) {
    Asset target = new Asset();
    target.setUcid(source.getUcid());
    target.setName(source.getName());
    target.setSymbol(source.getSymbol());
    target.setType(AssetType.valueOf(source.getType()));
    target.setClassification(AssetRank.valueOf(source.getClassification()));
    return target;
  }

  public static Asset toEntity(AssetDto source) {
    Asset target = new Asset();
    target.setId(source.getId());
    target.setUcid(source.getUcid());
    target.setName(source.getName());
    target.setSymbol(source.getSymbol());
    target.setType(AssetType.valueOf(source.getType()));
    target.setClassification(AssetRank.valueOf(source.getClassification()));
    return target;
  }

  public static AssetDto toDto(Asset source) {
    AssetDto target = new AssetDto();
    target.setId(source.getId());
    target.setUcid(source.getUcid());
    target.setName(source.getName());
    target.setSymbol(source.getSymbol());
    target.setType(source.getType().toString());
    target.setClassification(source.getClassification().toString());
    return target;
  }

  public static List<AssetDto> toDto(List<Asset> source) {
    return source.stream().map(p -> toDto(p)).collect(Collectors.toList());
  }

}
