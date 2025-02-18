package com.artcorb.bitbunker.models;

import java.math.BigDecimal;
import com.artcorb.bitbunker.enums.OrderType;
import com.artcorb.bitbunker.models.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "orders")
public class Order extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "asset_id")
  private Asset asset;

  @Enumerated(EnumType.STRING)
  private OrderType type;

  private BigDecimal quantity;

  private BigDecimal brlQuantity;

  private String notes;

}
