package com.artcorb.bitbunker.services;

import com.artcorb.bitbunker.dtos.TokenDto;

public interface TokenService {

  /**
   *
   * @param tokenDto - TokenDto object
   */
  void create(TokenDto tokenDto);

}
