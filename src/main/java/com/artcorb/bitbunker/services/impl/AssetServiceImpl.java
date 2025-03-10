package com.artcorb.bitbunker.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.artcorb.bitbunker.dtos.AssetDto;
import com.artcorb.bitbunker.dtos.CreateAssetDto;
import com.artcorb.bitbunker.dtos.CreateAssetParamsDto;
import com.artcorb.bitbunker.enums.AssetRank;
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
  public CreateAssetParamsDto getCreateAssetParams() {
    CreateAssetParamsDto params = new CreateAssetParamsDto();
    params.setTypeOptions(Arrays.stream(AssetType.values())
        .map(type -> Map.of("key", type.name(), "value", type.getLabel()))
        .collect(Collectors.toList()));
    params.setClassificationOptions(Arrays.stream(AssetRank.values())
        .map(type -> Map.of("key", type.name(), "value", type.getLabel()))
        .collect(Collectors.toList()));

    return params;
  }

}
