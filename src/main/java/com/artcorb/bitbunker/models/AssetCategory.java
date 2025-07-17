package com.artcorb.bitbunker.models;

import java.math.BigDecimal;
import com.artcorb.bitbunker.models.base.BaseEntity;
import jakarta.persistence.Entity;
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
@Table(name = "asset_categories")
public class AssetCategory extends BaseEntity {

  private String name;

  private BigDecimal recommendedPercentage;

}
