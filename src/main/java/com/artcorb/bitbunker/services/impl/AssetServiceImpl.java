package com.artcorb.bitbunker.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.artcorb.bitbunker.dtos.AssetDto;
import com.artcorb.bitbunker.dtos.AssetTierDto;
import com.artcorb.bitbunker.dtos.AssetTypeDto;
import com.artcorb.bitbunker.dtos.CreateAssetDto;
import com.artcorb.bitbunker.enums.AssetTier;
import com.artcorb.bitbunker.enums.AssetType;
import com.artcorb.bitbunker.exceptions.ResourceAlreadyExistsException;
import com.artcorb.bitbunker.exceptions.ResourceNotFoundException;
import com.artcorb.bitbunker.mappers.AssetMapper;
import com.artcorb.bitbunker.repos.AssetRepository;
import com.artcorb.bitbunker.services.AssetService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AssetServiceImpl implements AssetService {

  private AssetRepository repository;

  @Override
  public void create(CreateAssetDto assetDto) {
    if (repository.findByUcid(assetDto.getUcid()).isPresent())
      throw new ResourceAlreadyExistsException("Asset", "UCID", String.valueOf(assetDto.getUcid()));
    repository.save(AssetMapper.toEntity(assetDto));
  }

  @Override
  public void delete(long ucid) {
    repository.delete(repository.findByUcid(ucid)
        .orElseThrow(() -> new ResourceNotFoundException("Asset", "ID", String.valueOf(ucid))));
  }

  @Override
  public List<AssetDto> findAll() {
    return AssetMapper.toDto(repository.findAll());
  }

  @Override
  public List<AssetTypeDto> getAssetTypeOptions() {
    return Arrays.stream(AssetType.values())
        .map(type -> new AssetTypeDto(type.name(), type.getLabel())).collect(Collectors.toList());
  }

  @Override
  public List<AssetTierDto> getAssetTierOptions() {
    return Arrays.stream(AssetTier.values())
        .map(type -> new AssetTierDto(type.name(), type.getLabel())).collect(Collectors.toList());
  }

}
