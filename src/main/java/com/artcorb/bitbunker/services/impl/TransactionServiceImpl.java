package com.artcorb.bitbunker.services.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.artcorb.bitbunker.dtos.CreateBuyAndSellTransactionsDto;
import com.artcorb.bitbunker.dtos.CreateTransactionDto;
import com.artcorb.bitbunker.dtos.TransactionDto;
import com.artcorb.bitbunker.exceptions.ResourceNotFoundException;
import com.artcorb.bitbunker.mappers.TransactionMapper;
import com.artcorb.bitbunker.repos.TransactionRepository;
import com.artcorb.bitbunker.services.TransactionService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

  private TransactionRepository transactionRepository;

  @Override
  public void createBuyAndSellTransactions(CreateBuyAndSellTransactionsDto dto) {
    transactionRepository.saveAll(TransactionMapper.toEntity(dto));
  }

  @Override
  public void delete(long id) {
    transactionRepository.delete(transactionRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Transaction", "ID", String.valueOf(id))));
  }

  @Override
  public List<TransactionDto> findAll() {
    return TransactionMapper.toDto(transactionRepository.findAll());
  }

  @Override
  public void createTransactions(CreateTransactionDto dto) {
    transactionRepository.save(TransactionMapper.toEntity(dto));
  }
}
