package com.artcorb.bitbunker.services.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.artcorb.bitbunker.dtos.OrderDto;
import com.artcorb.bitbunker.exceptions.ResourceAlreadyExistsException;
import com.artcorb.bitbunker.exceptions.ResourceNotFoundException;
import com.artcorb.bitbunker.mappers.OrderMapper;
import com.artcorb.bitbunker.repos.OrderRepository;
import com.artcorb.bitbunker.services.OrderService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

  private OrderRepository orderRepository;

  @Override
  public void create(OrderDto dto) {
    if (orderRepository.findById(dto.getId()).isPresent())
      throw new ResourceAlreadyExistsException("Order", "ID", String.valueOf(dto.getId()));
    orderRepository.save(OrderMapper.toEntity(dto));
  }

  @Override
  public void delete(long id) {
    orderRepository.delete(orderRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Order", "ID", String.valueOf(id))));
  }

  @Override
  public List<OrderDto> findAll() {
    return OrderMapper.toDto(orderRepository.findAll());
  }
}
