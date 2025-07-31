package com.artcorb.bitbunker.services;

import java.util.List;
import com.artcorb.bitbunker.dtos.AssetCategoryDto;
import com.artcorb.bitbunker.dtos.AssetCategoryFormDto;

public interface AssetCategoryService {

  List<AssetCategoryDto> getAll();

  AssetCategoryDto create(AssetCategoryFormDto assetCategoryDto);

  AssetCategoryDto update(Long id, AssetCategoryFormDto assetCategoryDto);

  void delete(long id);
}
