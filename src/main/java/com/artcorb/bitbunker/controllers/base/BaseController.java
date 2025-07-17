package com.artcorb.bitbunker.controllers.base;

import com.artcorb.bitbunker.dtos.ResponseDto;
import jakarta.servlet.http.HttpServletRequest;

public class BaseController {

  protected static final String MESSAGE_200 = "Request processed successfully";
  protected static final String MESSAGE_201 = "Register created successfully";

  protected static ResponseDto buildResponse(HttpServletRequest request, Object data) {
    ResponseDto response = new ResponseDto(request.getRequestURI());
    response.setData(data);
    return response;
  }

}
