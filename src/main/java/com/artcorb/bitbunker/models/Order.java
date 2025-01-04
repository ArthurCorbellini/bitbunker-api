package com.artcorb.bitbunker.models;

import java.math.BigDecimal;
import com.artcorb.bitbunker.enums.OrderType;
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
public class Order extends BaseEntity {

  private Token token;

  private OrderType type;

  private BigDecimal quantity;

  private BigDecimal fiatCurrencyMoved;

  private String notes;

}
