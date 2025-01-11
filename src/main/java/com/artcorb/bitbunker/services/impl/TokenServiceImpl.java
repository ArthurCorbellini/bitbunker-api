package com.artcorb.bitbunker.services.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.artcorb.bitbunker.dtos.TokenDto;
import com.artcorb.bitbunker.exceptions.ResourceAlreadyExistsException;
import com.artcorb.bitbunker.exceptions.ResourceNotFoundException;
import com.artcorb.bitbunker.mappers.TokenMapper;
import com.artcorb.bitbunker.repos.TokenRepository;
import com.artcorb.bitbunker.services.TokenService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {

  private TokenRepository tokenRepository;

  @Override
  public void create(TokenDto tokenDto) {
    if (tokenRepository.findByUcid(tokenDto.getUcid()).isPresent())
      throw new ResourceAlreadyExistsException("Token", "UCID", String.valueOf(tokenDto.getUcid()));
    tokenRepository.save(TokenMapper.toToken(tokenDto));
  }

  @Override
  public void delete(long ucid) {
    tokenRepository.delete(tokenRepository.findByUcid(ucid)
        .orElseThrow(() -> new ResourceNotFoundException("Token", "UCID", String.valueOf(ucid))));
  }

  @Override
  public List<TokenDto> findAll() {
    return TokenMapper.toTokenDto(tokenRepository.findAll());
  }

}
