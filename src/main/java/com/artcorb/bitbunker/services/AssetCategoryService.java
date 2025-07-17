package com.artcorb.bitbunker.services;

import java.util.List;
import com.artcorb.bitbunker.dtos.AssetCategoryDto;
import com.artcorb.bitbunker.dtos.CreateAssetCategoryDto;

public interface AssetCategoryService {

  List<AssetCategoryDto> fetchAll();

  AssetCategoryDto create(CreateAssetCategoryDto assetCategoryDto);

  void delete(long id);
}
