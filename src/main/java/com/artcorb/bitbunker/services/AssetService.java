package com.artcorb.bitbunker.services;

import java.util.List;
import com.artcorb.bitbunker.dtos.AssetDto;
import com.artcorb.bitbunker.dtos.CreateAssetDto;

public interface AssetService {

  AssetDto create(CreateAssetDto assetDto);

  void delete(long ucid);

  List<AssetDto> findAll();

}
