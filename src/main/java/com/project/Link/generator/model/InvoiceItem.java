/**
 * Created By Chivo
 */


package com.project.Link.generator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class InvoiceItem {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Product product;
    private int quantity;

    public InvoiceItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public BigDecimal getItemTotal() {
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
    @Override
    public String  toString() {
        return String.format("%-20s %-10d $%.2f", product.getName(), quantity, getItemTotal());
    }
}
