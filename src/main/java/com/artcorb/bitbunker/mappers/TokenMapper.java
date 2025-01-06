package com.artcorb.bitbunker.mappers;

import com.artcorb.bitbunker.dtos.TokenDto;
import com.artcorb.bitbunker.enums.TokenRank;
import com.artcorb.bitbunker.models.Token;

public class TokenMapper {

  public static Token toToken(TokenDto source) {
    Token target = new Token();
    target.setUcid(source.getUcid());
    target.setName(source.getName());
    target.setSymbol(source.getSymbol());
    target.setClassification(TokenRank.valueOf(source.getClassification()));
    return target;
  }

  public static TokenDto toTokenDto(Token source) {
    TokenDto target = new TokenDto();
    target.setUcid(source.getUcid());
    target.setName(source.getName());
    target.setSymbol(source.getSymbol());
    target.setClassification(source.getClassification().toString());
    return target;
  }

}
