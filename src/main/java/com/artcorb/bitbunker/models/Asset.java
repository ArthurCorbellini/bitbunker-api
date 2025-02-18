package com.artcorb.bitbunker.models;

import com.artcorb.bitbunker.enums.AssetType;
import com.artcorb.bitbunker.enums.AssetRank;
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
@Table(name = "assets")
public class Asset extends BaseEntity {

  private long ucid;

  private String name;

  private String symbol;

  @Enumerated(EnumType.STRING)
  private AssetType type;

  @Enumerated(EnumType.STRING)
  private AssetRank classification;

}
