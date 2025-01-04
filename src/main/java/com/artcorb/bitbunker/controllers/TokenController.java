package com.artcorb.bitbunker.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
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
  public ResponseEntity<ResponseDto> createToken(@Valid @RequestParam TokenDto dto) {
    tokenService.create(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(STATUS_201, MESSAGE_201));
  }

}
