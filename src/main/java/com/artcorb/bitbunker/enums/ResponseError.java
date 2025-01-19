package com.artcorb.bitbunker.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseError {

  // @formatter:off
  VALIDATION_ERROR("Validation error"),
  ALREADY_EXISTS("Resource already exists"),
  NOT_FOUND("Resource not found"),
  INTERNAL_SERVER_ERROR("Internal server error");
  // @formatter:oN

  private String description;

}
