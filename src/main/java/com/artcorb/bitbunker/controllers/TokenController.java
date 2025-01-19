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
import com.artcorb.bitbunker.dtos.ResponseDto;
import com.artcorb.bitbunker.dtos.TokenDto;
import com.artcorb.bitbunker.services.TokenService;
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

@Tag(name = "CRUD REST API for Tokens", description = "CREATE, READ, UPDATE and DELETE tokens")
@AllArgsConstructor
@Validated
@RestController
@RequestMapping(path = "/token", produces = {MediaType.APPLICATION_JSON_VALUE})
public class TokenController extends BaseController {

  private TokenService tokenService;

  @Operation(summary = "Fetch all tokens REST API", description = "REST API to fetch all tokens")
  @ApiResponses({
      @ApiResponse(responseCode = "200",
          content = @Content(schema = @Schema(implementation = ResponseDto.class))),
      @ApiResponse(responseCode = "500",
          content = @Content(schema = @Schema(implementation = ResponseDto.class)))})
  @GetMapping
  public ResponseEntity<ResponseDto> fetchAll(HttpServletRequest request) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(buildResponse(request, tokenService.findAll()));
  }

  @Operation(summary = "Create token REST API", description = "REST API to create new token")
  @ApiResponses({
      @ApiResponse(responseCode = "201",
          content = @Content(schema = @Schema(implementation = ResponseDto.class))),
      @ApiResponse(responseCode = "400",
          content = @Content(schema = @Schema(implementation = ResponseDto.class))),
      @ApiResponse(responseCode = "409",
          content = @Content(schema = @Schema(implementation = ResponseDto.class))),
      @ApiResponse(responseCode = "500",
          content = @Content(schema = @Schema(implementation = ResponseDto.class)))})
  @PostMapping
  public ResponseEntity<ResponseDto> create(HttpServletRequest request,
      @Valid @RequestBody TokenDto dto) {
    tokenService.create(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(buildResponse(request, MESSAGE_201));
  }

  @Operation(summary = "Delete Token REST API",
      description = "REST API to delete Token based on the UCID")
  @ApiResponses({
      @ApiResponse(responseCode = "200",
          content = @Content(schema = @Schema(implementation = ResponseDto.class))),
      @ApiResponse(responseCode = "404",
          content = @Content(schema = @Schema(implementation = ResponseDto.class))),
      @ApiResponse(responseCode = "500",
          content = @Content(schema = @Schema(implementation = ResponseDto.class)))})
  @DeleteMapping
  public ResponseEntity<ResponseDto> delete(HttpServletRequest request,
      @Valid @RequestParam @NotNull(message = "UCID can not be a null") @Positive(
          message = "UCID must be a positive number") long ucid) {
    tokenService.delete(ucid);
    return ResponseEntity.status(HttpStatus.OK).body(buildResponse(request, MESSAGE_200));
  }

}
