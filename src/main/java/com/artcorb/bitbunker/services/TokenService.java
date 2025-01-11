package com.artcorb.bitbunker.services;

import java.util.List;
import com.artcorb.bitbunker.dtos.TokenDto;

public interface TokenService {

  void create(TokenDto tokenDto);

  void delete(long ucid);

  List<TokenDto> findAll();

}
