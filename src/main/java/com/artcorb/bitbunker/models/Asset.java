package com.artcorb.bitbunker.models;

import com.artcorb.bitbunker.models.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

  private Long ucid;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private AssetCategory category;

  private String symbol;

  private String name;

}
