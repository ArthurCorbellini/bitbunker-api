package com.artcorb.bitbunker.services.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.artcorb.bitbunker.dtos.AssetCategoryDto;
import com.artcorb.bitbunker.dtos.AssetCategoryFormDto;
import com.artcorb.bitbunker.exceptions.ResourceNotFoundException;
import com.artcorb.bitbunker.mappers.AssetCategoryMapper;
import com.artcorb.bitbunker.models.AssetCategory;
import com.artcorb.bitbunker.repos.AssetCategoryRepository;
import com.artcorb.bitbunker.services.AssetCategoryService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AssetCategoryServiceImpl implements AssetCategoryService {

  private AssetCategoryRepository repository;

  @Override
  public List<AssetCategoryDto> getAll() {
    return AssetCategoryMapper.toDto(repository.findAll());
  }

  @Override
  public AssetCategoryDto create(AssetCategoryFormDto assetCategoryDto) {
    return AssetCategoryMapper
        .toDto(repository.save(AssetCategoryMapper.toEntity(assetCategoryDto)));
  }

  @Override
  public AssetCategoryDto update(Long id, AssetCategoryFormDto assetCategoryDto) {
    if (!repository.existsById(id))
      new ResourceNotFoundException("Asset Category", "ID", String.valueOf(id));

    AssetCategory entity = AssetCategoryMapper.toEntity(assetCategoryDto);
    entity.setId(id);

    return AssetCategoryMapper.toDto(repository.save(entity));
  }

  @Override
  public void delete(long id) {
    repository.delete(repository.findById(id).orElseThrow(
        () -> new ResourceNotFoundException("Asset Category", "ID", String.valueOf(id))));
  }

}
