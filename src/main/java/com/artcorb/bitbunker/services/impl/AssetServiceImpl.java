package com.artcorb.bitbunker.services.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.artcorb.bitbunker.dtos.AssetDto;
import com.artcorb.bitbunker.dtos.CreateAssetDto;
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
  public List<AssetDto> findAll() {
    return AssetMapper.toDto(repository.findAll());
  }

  @Override
  public AssetDto create(CreateAssetDto assetDto) {
    if (repository.findByUcid(assetDto.getUcid()).isPresent())
      throw new ResourceAlreadyExistsException("Asset", "UCID", String.valueOf(assetDto.getUcid()));
    return AssetMapper.toDto(repository.save(AssetMapper.toEntity(assetDto)));
  }

  @Override
  public void delete(long ucid) {
    repository.delete(repository.findByUcid(ucid)
        .orElseThrow(() -> new ResourceNotFoundException("Asset", "UCID", String.valueOf(ucid))));
  }

}
