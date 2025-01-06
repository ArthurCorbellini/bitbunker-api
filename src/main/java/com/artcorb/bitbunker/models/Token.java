package com.artcorb.bitbunker.models;

import com.artcorb.bitbunker.enums.TokenRank;
import com.artcorb.bitbunker.models.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tokens")
public class Token extends BaseEntity {

  private long ucid;

  private String name;

  private String symbol;

  @Enumerated(EnumType.STRING)
  private TokenRank classification;

}
