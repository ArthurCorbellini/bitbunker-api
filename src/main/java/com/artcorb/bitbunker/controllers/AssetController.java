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
import com.artcorb.bitbunker.dtos.CreateAssetDto;
import com.artcorb.bitbunker.dtos.ResponseDto;
import com.artcorb.bitbunker.services.AssetService;
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

@Tag(name = "CRUD REST API for Assets", description = "CREATE, READ, UPDATE and DELETE assets")
@AllArgsConstructor
@Validated
@RestController
@RequestMapping(path = "/asset", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AssetController extends BaseController {

  private AssetService assetService;

  @Operation(summary = "Fetch all assets REST API", description = "REST API to fetch all assets")
  @ApiResponses({
      @ApiResponse(responseCode = "200",
          content = @Content(schema = @Schema(implementation = ResponseDto.class))),
      @ApiResponse(responseCode = "500",
          content = @Content(schema = @Schema(implementation = ResponseDto.class)))})
  @GetMapping
  public ResponseEntity<ResponseDto> fetchAllAssets(HttpServletRequest request) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(buildResponse(request, assetService.findAll()));
  }

  @Operation(summary = "Create asset REST API", description = "REST API to create new asset")
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
  public ResponseEntity<ResponseDto> createAsset(HttpServletRequest request,
      @Valid @RequestBody CreateAssetDto dto) {
    assetService.create(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(buildResponse(request, MESSAGE_201));
  }

  @Operation(summary = "Get create asset required params REST API",
      description = "Return all params for create an Asset")
  @ApiResponses({
      @ApiResponse(responseCode = "200",
          content = @Content(schema = @Schema(implementation = ResponseDto.class))),
      @ApiResponse(responseCode = "500",
          content = @Content(schema = @Schema(implementation = ResponseDto.class)))})
  @GetMapping("/create-params")
  public ResponseEntity<ResponseDto> getCreateAssetParams(HttpServletRequest request) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(buildResponse(request, assetService.getCreateAssetParams()));
  }

  @Operation(summary = "Delete Asset REST API",
      description = "REST API to delete Asset based on the UCID")
  @ApiResponses({
      @ApiResponse(responseCode = "200",
          content = @Content(schema = @Schema(implementation = ResponseDto.class))),
      @ApiResponse(responseCode = "404",
          content = @Content(schema = @Schema(implementation = ResponseDto.class))),
      @ApiResponse(responseCode = "500",
          content = @Content(schema = @Schema(implementation = ResponseDto.class)))})
  @DeleteMapping
  public ResponseEntity<ResponseDto> deleteAsset(HttpServletRequest request,
      @Valid @RequestParam @NotNull(message = "UCID can not be a null") @Positive(
          message = "UCID must be a positive number") long ucid) {
    assetService.delete(ucid);
    return ResponseEntity.status(HttpStatus.OK).body(buildResponse(request, MESSAGE_200));
  }

}
