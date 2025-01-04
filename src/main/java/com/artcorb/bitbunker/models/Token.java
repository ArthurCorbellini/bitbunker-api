package com.artcorb.bitbunker.models;

import com.artcorb.bitbunker.enums.TokenRank;
import com.artcorb.bitbunker.models.bases.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Token extends BaseEntity {

  private long ucid;

  private String name;

  private String symbol;

  private TokenRank rank;

}
