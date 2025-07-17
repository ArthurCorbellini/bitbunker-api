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
import com.artcorb.bitbunker.dtos.CreateAssetCategoryDto;
import com.artcorb.bitbunker.dtos.ResponseDto;
import com.artcorb.bitbunker.services.AssetCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@Tag(name = "Asset Categories", description = "REST API for Asset Categories")
@AllArgsConstructor
@Validated
@RestController
@RequestMapping(path = "/asset-category", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AssetCategoryController {

  private final ResponseBuilder rb;
  private final AssetCategoryService assetCategoryService;

  @Operation(summary = "Fetch All Asset Categories",
      description = "Retrieves a list of all registered asset categories")
  @ApiResponses({
      @ApiResponse(responseCode = "200",
          description = "List of asset categories retrieved successfully",
          content = @Content(schema = @Schema(implementation = ResponseDto.class))),
      @ApiResponse(responseCode = "500", description = "Internal server error",
          content = @Content(schema = @Schema(implementation = ResponseDto.class)))})
  @GetMapping
  public ResponseEntity<ResponseDto> fetchAllAssetCategories() {
    return rb.ok(assetCategoryService.fetchAll());
  }

  @Operation(summary = "Create Asset Category",
      description = "Creates a new asset category with the given data")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "Asset category created successfully",
          content = @Content(schema = @Schema(implementation = ResponseDto.class))),
      @ApiResponse(responseCode = "400", description = "Invalid input data (validation errors)",
          content = @Content(schema = @Schema(implementation = ResponseDto.class))),
      @ApiResponse(responseCode = "500", description = "Internal server error",
          content = @Content(schema = @Schema(implementation = ResponseDto.class)))})
  @PostMapping
  public ResponseEntity<ResponseDto> createAssetCategory(
      @Valid @RequestBody CreateAssetCategoryDto dto) {
    return rb.created(assetCategoryService.create(dto));
  }

  @Operation(summary = "Delete Asset Category", description = "Deletes an asset category by its ID")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Asset category deleted successfully",
          content = @Content(schema = @Schema(implementation = ResponseDto.class))),
      @ApiResponse(responseCode = "404", description = "Asset category not found",
          content = @Content(schema = @Schema(implementation = ResponseDto.class))),
      @ApiResponse(responseCode = "500", description = "Internal server error",
          content = @Content(schema = @Schema(implementation = ResponseDto.class)))})
  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDto> deleteAssetCategory(
      @PathVariable @Positive(message = "Id must be a positive number") long id) {
    assetCategoryService.delete(id);
    return rb.ok();
  }

}
