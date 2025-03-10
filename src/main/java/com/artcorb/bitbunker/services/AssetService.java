package com.artcorb.bitbunker.services;

import java.util.List;
import com.artcorb.bitbunker.dtos.AssetDto;
import com.artcorb.bitbunker.dtos.CreateAssetDto;
import com.artcorb.bitbunker.dtos.CreateAssetParamsDto;

public interface AssetService {

  void create(CreateAssetDto assetDto);

  CreateAssetParamsDto getCreateAssetParams();

  void delete(long ucid);

  List<AssetDto> findAll();

}
