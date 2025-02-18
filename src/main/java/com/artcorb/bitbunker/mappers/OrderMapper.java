package com.artcorb.bitbunker.mappers;

import java.util.List;
import java.util.stream.Collectors;
import com.artcorb.bitbunker.dtos.AssetDto;
import com.artcorb.bitbunker.dtos.OrderDto;
import com.artcorb.bitbunker.enums.OrderType;
import com.artcorb.bitbunker.models.Asset;
import com.artcorb.bitbunker.models.Order;

public class OrderMapper {

  public static Order toEntity(OrderDto source) {
    Order target = new Order();
    target.setId(source.getId());
    target.setType(OrderType.valueOf(source.getType()));
    target.setQuantity(source.getQuantity());
    target.setFiatCurrencyMoved(source.getFiatCurrencyMoved());
    target.setNotes(source.getNotes());

    Asset asset = AssetMapper.toEntity(source.getAsset());
    target.setAsset(asset);

    return target;
  }

  public static OrderDto toDto(Order source) {
    OrderDto target = new OrderDto();
    target.setId(source.getId());
    target.setType(source.getType().toString());
    target.setQuantity(source.getQuantity());
    target.setFiatCurrencyMoved(source.getFiatCurrencyMoved());
    target.setNotes(source.getNotes());

    AssetDto assetDto = AssetMapper.toDto(source.getAsset());
    target.setAsset(assetDto);

    return target;
  }

  public static List<OrderDto> toDto(List<Order> source) {
    return source.stream().map(p -> toDto(p)).collect(Collectors.toList());
  }

}
