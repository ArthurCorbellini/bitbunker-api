package com.artcorb.bitbunker.mappers;

import java.util.List;
import java.util.stream.Collectors;
import com.artcorb.bitbunker.dtos.CreateSwapTransactionsDto;
import com.artcorb.bitbunker.dtos.CreateTransactionDto;
import com.artcorb.bitbunker.dtos.TransactionDto;
import com.artcorb.bitbunker.enums.TransactionType;
import com.artcorb.bitbunker.models.Asset;
import com.artcorb.bitbunker.models.Transaction;

public class TransactionMapper {

  public static Transaction toEntity(CreateTransactionDto source) {
    Transaction entity = new Transaction();
    entity.setType(TransactionType.valueOf(source.getType()));
    entity.setDateTime(source.getDateTime());
    entity.setQuantity(source.getQuantity());
    entity.setUnitPrice(source.getUnitPrice());
    entity.setFee(source.getFee());
    entity.setNotes(source.getNotes());

    Asset asset = new Asset();
    asset.setId(source.getAssetId());
    entity.setAsset(asset);

    return entity;
  }

  public static List<Transaction> toEntity(CreateSwapTransactionsDto source) {
    Transaction in = new Transaction();
    in.setType(TransactionType.IN);
    in.setDateTime(source.getDateTime());
    in.setNotes(source.getNotes());
    in.setQuantity(source.getSwapIn().getQuantity());
    in.setUnitPrice(source.getSwapIn().getUnitPrice());
    in.setFee(source.getSwapIn().getFee());

    Asset assetIn = new Asset();
    assetIn.setId(source.getSwapIn().getAssetId());
    in.setAsset(assetIn);

    Transaction out = new Transaction();
    out.setType(TransactionType.OUT);
    out.setDateTime(source.getDateTime());
    out.setNotes(source.getNotes());
    out.setQuantity(source.getSwapOut().getQuantity());
    out.setUnitPrice(source.getSwapOut().getUnitPrice());
    out.setFee(source.getSwapOut().getFee());

    Asset assetOut = new Asset();
    assetOut.setId(source.getSwapOut().getAssetId());
    out.setAsset(assetOut);

    return List.of(in, out);
  }

  public static TransactionDto toDto(Transaction source) {
    TransactionDto target = new TransactionDto();
    target.setId(source.getId());
    target.setAsset(AssetMapper.toDto(source.getAsset()));
    target.setType(source.getType().toString());
    target.setDateTime(source.getDateTime());
    target.setQuantity(source.getQuantity());
    target.setUnitPrice(source.getUnitPrice());
    target.setFee(source.getFee());
    target.setNotes(source.getNotes());
    return target;
  }

  public static List<TransactionDto> toDto(List<Transaction> source) {
    return source.stream().map(p -> toDto(p)).collect(Collectors.toList());
  }

}
