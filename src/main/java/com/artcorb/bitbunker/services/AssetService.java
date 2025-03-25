package com.artcorb.bitbunker.services;

import java.util.List;
import com.artcorb.bitbunker.dtos.AssetDto;
import com.artcorb.bitbunker.dtos.AssetTierDto;
import com.artcorb.bitbunker.dtos.AssetTypeDto;
import com.artcorb.bitbunker.dtos.CreateAssetDto;

public interface AssetService {

  void create(CreateAssetDto assetDto);

  void delete(long ucid);

  List<AssetDto> findAll();

  List<AssetTypeDto> getAssetTypeOptions();

  List<AssetTierDto> getAssetTierOptions();

}
