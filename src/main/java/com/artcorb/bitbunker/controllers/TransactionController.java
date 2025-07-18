package com.artcorb.bitbunker.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.artcorb.bitbunker.common.MyResponseBuilder;
import com.artcorb.bitbunker.dtos.CreateSwapTransactionsDto;
import com.artcorb.bitbunker.dtos.CreateTransactionDto;
import com.artcorb.bitbunker.dtos.ResponseDto;
import com.artcorb.bitbunker.services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@Tag(name = "Transactions", description = "REST API for Transactions")
@AllArgsConstructor
@Validated
@RestController
@RequestMapping(path = "/transaction", produces = {MediaType.APPLICATION_JSON_VALUE})
public class TransactionController {

  private final MyResponseBuilder mrb;
  private final TransactionService transactionService;

  @Operation(summary = "Fetch all Transactions",
      description = "Retrieves a list of all registered transactions")
  @ApiResponses({
      @ApiResponse(responseCode = "200",
          description = "List of transactions retrieved successfully",
          content = @Content(schema = @Schema(implementation = ResponseDto.class))),
      @ApiResponse(responseCode = "500", description = "Internal server error",
          content = @Content(schema = @Schema(implementation = ResponseDto.class)))})
  @GetMapping
  public ResponseEntity<ResponseDto> fetchTransactions() {
    return mrb.ok(transactionService.findAll());
  }

  @Operation(summary = "Create Transaction",
      description = "Creates a new trasaction with the given data")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "Transaction created successfully",
          content = @Content(schema = @Schema(implementation = ResponseDto.class))),
      @ApiResponse(responseCode = "400", description = "Invalid input data (validation errors)",
          content = @Content(schema = @Schema(implementation = ResponseDto.class))),
      @ApiResponse(responseCode = "500", description = "Internal server error",
          content = @Content(schema = @Schema(implementation = ResponseDto.class)))})
  @PostMapping
  public ResponseEntity<ResponseDto> createTransaction(
      @Valid @RequestBody CreateTransactionDto dto) {
    return mrb.created(transactionService.createTransaction(dto));
  }

  @Operation(summary = "Delete Transaction", description = "Deletes an asset category by its ID")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Transaction category deleted successfully",
          content = @Content(schema = @Schema(implementation = ResponseDto.class))),
      @ApiResponse(responseCode = "404", description = "Transaction not found",
          content = @Content(schema = @Schema(implementation = ResponseDto.class))),
      @ApiResponse(responseCode = "500", description = "Internal server error",
          content = @Content(schema = @Schema(implementation = ResponseDto.class)))})
  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDto> delete(
      @PathVariable @Positive(message = "ID must be a positive number") long id) {
    transactionService.delete(id);
    return mrb.ok();
  }

  @Operation(summary = "Create Swap Transactions",
      description = "Creates a new swap transaction with the given data")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "Swap transaction created successfully",
          content = @Content(schema = @Schema(implementation = ResponseDto.class))),
      @ApiResponse(responseCode = "400", description = "Invalid input data (validation errors)",
          content = @Content(schema = @Schema(implementation = ResponseDto.class))),
      @ApiResponse(responseCode = "500", description = "Internal server error",
          content = @Content(schema = @Schema(implementation = ResponseDto.class)))})
  @PostMapping("/swap")
  public ResponseEntity<ResponseDto> createSwapTransactions(
      @Valid @RequestBody CreateSwapTransactionsDto dto) {
    return mrb.created(transactionService.createSwapTransactions(dto));
  }

}
