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
import com.artcorb.bitbunker.common.ResponseBuilder;
import com.artcorb.bitbunker.dtos.CreateAssetDto;
import com.artcorb.bitbunker.dtos.ResponseDto;
import com.artcorb.bitbunker.services.AssetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@Tag(name = "Asset", description = "REST API for Asset")
@AllArgsConstructor
@Validated
@RestController
@RequestMapping(path = "/asset", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AssetController {

  private final ResponseBuilder rb;
  private final AssetService assetService;

  @Operation(summary = "Fetch all Assets", description = "Retrieves a list of all registered asset")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "List of asset retrieved successfully",
          content = @Content(schema = @Schema(implementation = ResponseDto.class))),
      @ApiResponse(responseCode = "500", description = "Internal server error",
          content = @Content(schema = @Schema(implementation = ResponseDto.class)))})
  @GetMapping
  public ResponseEntity<ResponseDto> fetchAllAssets() {
    return rb.ok(assetService.findAll());
  }

  @Operation(summary = "Create Asset", description = "Creates a new asset with the given data")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "Asset created successfully",
          content = @Content(schema = @Schema(implementation = ResponseDto.class))),
      @ApiResponse(responseCode = "400", description = "Invalid input data (validation errors)",
          content = @Content(schema = @Schema(implementation = ResponseDto.class))),
      @ApiResponse(responseCode = "409", description = "An asset with the same UCID already exists",
          content = @Content(schema = @Schema(implementation = ResponseDto.class))),
      @ApiResponse(responseCode = "500", description = "Internal server error",
          content = @Content(schema = @Schema(implementation = ResponseDto.class)))})
  @PostMapping
  public ResponseEntity<ResponseDto> createAsset(@Valid @RequestBody CreateAssetDto dto) {
    return rb.created(assetService.create(dto));
  }

  @Operation(summary = "Delete Asset", description = "Deletes an asset category by its UCID")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Asset deleted successfully",
          content = @Content(schema = @Schema(implementation = ResponseDto.class))),
      @ApiResponse(responseCode = "404", description = "Asset not found",
          content = @Content(schema = @Schema(implementation = ResponseDto.class))),
      @ApiResponse(responseCode = "500", description = "Internal server error",
          content = @Content(schema = @Schema(implementation = ResponseDto.class)))})
  @DeleteMapping("/{ucid}")
  public ResponseEntity<ResponseDto> deleteAsset(
      @PathVariable @Positive(message = "UCID must be a positive number") long ucid) {
    assetService.delete(ucid);
    return rb.ok();
  }

}
