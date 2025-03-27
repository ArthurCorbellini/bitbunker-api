package com.artcorb.bitbunker.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.artcorb.bitbunker.dtos.AssetDto;
import com.artcorb.bitbunker.dtos.CreateBuyAndSellTransactionsDto;
import com.artcorb.bitbunker.dtos.TransactionDto;
import com.artcorb.bitbunker.enums.TransactionType;
import com.artcorb.bitbunker.models.Asset;
import com.artcorb.bitbunker.models.Transaction;

public class TransactionMapper {

  public static List<Transaction> toEntity(CreateBuyAndSellTransactionsDto source) {
    Transaction sell = new Transaction();
    sell.setType(TransactionType.SELL);
    sell.setDateTime(source.getDate());
    sell.setNotes(source.getNotes());

    Asset sellAsset = new Asset();
    sellAsset.setId(source.getSell().getAssetId());
    sell.setAsset(sellAsset);
    sell.setAmount(source.getSell().getAmount());
    sell.setUnitPrice(source.getSell().getUnitPrice());
    sell.setTotalValue(source.getSell().getTotalValue());

    Transaction buy = new Transaction();
    buy.setType(TransactionType.BUY);
    buy.setDateTime(source.getDate());
    buy.setNotes(source.getNotes());

    Asset buyAsset = new Asset();
    buyAsset.setId(source.getBuy().getAssetId());
    buy.setAsset(buyAsset);
    buy.setAmount(source.getBuy().getAmount());
    buy.setUnitPrice(source.getBuy().getUnitPrice());
    buy.setTotalValue(source.getBuy().getTotalValue());

    List<Transaction> transactions = new ArrayList<>();
    transactions.add(buy);
    transactions.add(sell);

    return transactions;
  }

  public static TransactionDto toDto(Transaction source) {
    TransactionDto target = new TransactionDto();
    target.setId(source.getId());
    target.setType(source.getType().toString());
    target.setAmount(source.getAmount());
    target.setUnitPrice(source.getUnitPrice());
    target.setTotalValue(source.getTotalValue());
    target.setDateTime(source.getDateTime());
    target.setNotes(source.getNotes());

    AssetDto assetDto = AssetMapper.toDto(source.getAsset());
    target.setAsset(assetDto);

    return target;
  }

  public static List<TransactionDto> toDto(List<Transaction> source) {
    return source.stream().map(p -> toDto(p)).collect(Collectors.toList());
  }

}
