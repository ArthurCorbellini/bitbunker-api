package com.artcorb.bitbunker.services;

import com.artcorb.bitbunker.dtos.TokenDto;

public interface TokenService {

  void create(TokenDto tokenDto);

  void delete(long ucid);

}
