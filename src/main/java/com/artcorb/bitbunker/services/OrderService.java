package com.artcorb.bitbunker.services;

import java.util.List;
import com.artcorb.bitbunker.dtos.OrderDto;

public interface OrderService {

  void create(OrderDto dto);

  void delete(long id);

  List<OrderDto> findAll();

}
