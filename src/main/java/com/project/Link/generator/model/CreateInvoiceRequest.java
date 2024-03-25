/**
 * Created By Chivo
 */


package com.project.Link.generator.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateInvoiceRequest {
    private List<InvoiceItem> items;
}
