package com.artcorb.bitbunker.services;

import java.util.List;
import com.artcorb.bitbunker.dtos.CreateSwapTransactionsDto;
import com.artcorb.bitbunker.dtos.CreateTransactionDto;
import com.artcorb.bitbunker.dtos.TransactionDto;

public interface TransactionService {

  List<TransactionDto> findAll();

  TransactionDto createTransaction(CreateTransactionDto dto);

  void delete(long id);

  List<TransactionDto> createSwapTransactions(CreateSwapTransactionsDto dto);

}
