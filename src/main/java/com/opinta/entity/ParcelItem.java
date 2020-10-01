package com.opinta.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class ParcelItem {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private long quantity;
    private float weigh;
    private BigDecimal price;

    public ParcelItem(String name, long quantity, float weigh, BigDecimal price) {
        this.name = name;
        this.quantity = quantity;
        this.weigh = weigh;
        this.price = price;
    }
}
