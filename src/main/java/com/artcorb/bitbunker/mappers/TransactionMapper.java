package com.artcorb.bitbunker.mappers;

import java.util.List;
import java.util.stream.Collectors;
import com.artcorb.bitbunker.dtos.AssetDto;
import com.artcorb.bitbunker.dtos.TransactionDto;
import com.artcorb.bitbunker.enums.TransactionType;
import com.artcorb.bitbunker.models.Asset;
import com.artcorb.bitbunker.models.Transaction;

public class TransactionMapper {

  public static Transaction toEntity(TransactionDto source) {
    Transaction target = new Transaction();
    target.setId(source.getId());
    target.setType(TransactionType.valueOf(source.getType()));
    target.setQuantity(source.getQuantity());
    target.setBrlQuantity(source.getBrlQuantity());
    target.setNotes(source.getNotes());

    Asset asset = AssetMapper.toEntity(source.getAsset());
    target.setAsset(asset);

    return target;
  }

  public static TransactionDto toDto(Transaction source) {
    TransactionDto target = new TransactionDto();
    target.setId(source.getId());
    target.setType(source.getType().toString());
    target.setQuantity(source.getQuantity());
    target.setBrlQuantity(source.getBrlQuantity());
    target.setNotes(source.getNotes());

    AssetDto assetDto = AssetMapper.toDto(source.getAsset());
    target.setAsset(assetDto);

    return target;
  }

  public static List<TransactionDto> toDto(List<Transaction> source) {
    return source.stream().map(p -> toDto(p)).collect(Collectors.toList());
  }

}
