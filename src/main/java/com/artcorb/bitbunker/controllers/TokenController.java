package com.artcorb.bitbunker.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.artcorb.bitbunker.controllers.base.BaseController;
import com.artcorb.bitbunker.dtos.ResponseDto;
import com.artcorb.bitbunker.dtos.ResponseErrorDto;
import com.artcorb.bitbunker.dtos.TokenDto;
import com.artcorb.bitbunker.services.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@Tag(name = "CRUD REST API for Tokens", description = "CREATE, READ, UPDATE and DELETE tokens")
@AllArgsConstructor
@Validated
@RestController
@RequestMapping(path = "/token", produces = {MediaType.APPLICATION_JSON_VALUE})
public class TokenController extends BaseController {

  private TokenService tokenService;

  @Operation(summary = "Create token REST API", description = "REST API to create new token")
  @ApiResponses({@ApiResponse(responseCode = STATUS_201, description = MESSAGE_201),
      @ApiResponse(responseCode = STATUS_500, description = MESSAGE_500,
          content = @Content(schema = @Schema(implementation = ResponseErrorDto.class)))})
  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createToken(@Valid @RequestBody TokenDto dto) {
    tokenService.create(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(RESPONSE_201);
  }

  @Operation(summary = "Delete Token REST API",
      description = "REST API to delete Token based on the UCID")
  @ApiResponses({@ApiResponse(responseCode = STATUS_200, description = MESSAGE_200),
      @ApiResponse(responseCode = STATUS_417, description = MESSAGE_417_DELETE),
      @ApiResponse(responseCode = STATUS_500, description = MESSAGE_500,
          content = @Content(schema = @Schema(implementation = ResponseErrorDto.class)))})
  @DeleteMapping("/delete")
  public ResponseEntity<ResponseDto> deleteToken(@RequestParam @Pattern(regexp = "^\\d{1,10}$",
      message = "UCID must have 1 to 10 digits") long ucid) {
    tokenService.delete(ucid);
    return ResponseEntity.status(HttpStatus.OK).body(RESPONSE_200);
  }

}
