package com.artcorb.bitbunker.services.impl;

import org.springframework.stereotype.Service;
import com.artcorb.bitbunker.dtos.TokenDto;
import com.artcorb.bitbunker.mappers.TokenMapper;
import com.artcorb.bitbunker.models.Token;
import com.artcorb.bitbunker.repos.TokenRepository;
import com.artcorb.bitbunker.services.TokenService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {

  private TokenRepository tokenRepository;

  @Override
  public void create(TokenDto tokenDto) {
    Token entity = TokenMapper.toToken(tokenDto);

    // TODO validate if the ucid token already exist, with global exception handler

    tokenRepository.save(entity);
  }

}
