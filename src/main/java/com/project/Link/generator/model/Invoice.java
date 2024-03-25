/**
 * Created By Chivo
 */


package com.project.Link.generator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity
public class Invoice {
    @Id
    @UuidGenerator
    private String id;
    @OneToMany(cascade = CascadeType.ALL)
    private List<InvoiceItem> items;
    public Invoice() {
        items = new ArrayList<>();
    }
    public void addItem(InvoiceItem item) {
        items.add(item);
    }

    @JsonIgnore
    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (InvoiceItem item : items) {
            total = total.add(item.getItemTotal());
        }
        return total;
    }
    public void printInvoice() {
        System.out.println("********** Invoice **********");
        System.out.println("Product Name        Quantity  Price");
        System.out.println("---------------------------------");
        for (InvoiceItem item : items) {
            System.out.println(item);
        }
        System.out.println("---------------------------------");
        System.out.printf("Total: $%.2f%n", getTotal());
        System.out.println("*******************************");
    }
}
