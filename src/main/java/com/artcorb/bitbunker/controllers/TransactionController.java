package com.artcorb.bitbunker.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.artcorb.bitbunker.controllers.base.BaseController;
import com.artcorb.bitbunker.dtos.CreateBuyAndSellTransactionsDto;
import com.artcorb.bitbunker.dtos.ResponseDto;
import com.artcorb.bitbunker.services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@Tag(name = "Transactions", description = "REST API for Transactions")
@AllArgsConstructor
@Validated
@RestController
@RequestMapping(path = "/transaction", produces = {MediaType.APPLICATION_JSON_VALUE})
public class TransactionController extends BaseController {

  private TransactionService transactionService;

  @Operation(summary = "Fetch all transactions", description = "REST API to fetch all transactions")
  @ApiResponses({
      @ApiResponse(responseCode = "200",
          content = @Content(schema = @Schema(implementation = ResponseDto.class))),
      @ApiResponse(responseCode = "500",
          content = @Content(schema = @Schema(implementation = ResponseDto.class)))})
  @GetMapping
  public ResponseEntity<ResponseDto> fetchAll(HttpServletRequest request) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(buildResponse(request, transactionService.findAll()));
  }

  @Operation(summary = "Create Buy and Sell Transactions",
      description = "REST API to create buy and sell transactions")
  @ApiResponses({
      @ApiResponse(responseCode = "201",
          content = @Content(schema = @Schema(implementation = ResponseDto.class))),
      @ApiResponse(responseCode = "400",
          content = @Content(schema = @Schema(implementation = ResponseDto.class))),
      @ApiResponse(responseCode = "500",
          content = @Content(schema = @Schema(implementation = ResponseDto.class)))})
  @PostMapping("/buy-and-sell")
  public ResponseEntity<ResponseDto> createBuyAndSellTransactions(HttpServletRequest request,
      @Valid @RequestBody CreateBuyAndSellTransactionsDto dto) {
    transactionService.createBuyAndSellTransactions(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(buildResponse(request, MESSAGE_201));
  }

  @Operation(summary = "Delete Transaction",
      description = "REST API to delete Transaction based on the ID")
  @ApiResponses({
      @ApiResponse(responseCode = "200",
          content = @Content(schema = @Schema(implementation = ResponseDto.class))),
      @ApiResponse(responseCode = "404",
          content = @Content(schema = @Schema(implementation = ResponseDto.class))),
      @ApiResponse(responseCode = "500",
          content = @Content(schema = @Schema(implementation = ResponseDto.class)))})
  @DeleteMapping
  public ResponseEntity<ResponseDto> delete(HttpServletRequest request,
      @Valid @RequestParam @NotNull(message = "ID can not be a null") @Positive(
          message = "ID must be a positive number") long id) {
    transactionService.delete(id);
    return ResponseEntity.status(HttpStatus.OK).body(buildResponse(request, MESSAGE_200));
  }

}
