package com.artcorb.bitbunker.controllers.base;

import com.artcorb.bitbunker.dtos.ResponseDto;

public class BaseController {

  protected static final String STATUS_200 = "200";
  protected static final String MESSAGE_200 = "Request processed successfully";

  protected static final String STATUS_201 = "201";
  protected static final String MESSAGE_201 = "Register created successfully";

  protected static final String STATUS_400 = "400";
  protected static final String MESSAGE_400 = "The server is unable to process a request";

  protected static final String STATUS_417 = "417";
  protected static final String MESSAGE_417 =
      "Expectation failed. Please try again or contact Dev team";

  protected static final String STATUS_500 = "500";
  protected static final String MESSAGE_500 =
      "An error occurred. Please try again or contact Dev team";

  protected static final ResponseDto RESPONSE_200 = new ResponseDto(STATUS_200, MESSAGE_200);
  protected static final ResponseDto RESPONSE_201 = new ResponseDto(STATUS_201, MESSAGE_201);
  protected static final ResponseDto RESPONSE_417 = new ResponseDto(STATUS_417, MESSAGE_417);
  protected static final ResponseDto RESPONSE_500 = new ResponseDto(STATUS_500, MESSAGE_500);

}
