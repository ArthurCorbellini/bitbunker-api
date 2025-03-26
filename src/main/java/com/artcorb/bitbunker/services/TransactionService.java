package com.artcorb.bitbunker.services;

import java.util.List;
import com.artcorb.bitbunker.dtos.TransactionDto;

public interface TransactionService {

  void create(TransactionDto dto);

  void delete(long id);

  List<TransactionDto> findAll();

}
