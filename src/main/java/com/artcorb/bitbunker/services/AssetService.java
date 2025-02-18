package com.artcorb.bitbunker.services;

import java.util.List;
import com.artcorb.bitbunker.dtos.AssetDto;

public interface AssetService {

  void create(AssetDto assetDto);

  void delete(long ucid);

  List<AssetDto> findAll();

}
