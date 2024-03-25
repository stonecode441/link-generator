/**
 * Created By Chivo
 */


package com.project.Link.generator;

import com.project.Link.generator.model.*;
import com.project.Link.generator.service.InvoiceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class InvoiceServiceTest {
    @Autowired
    private InvoiceService invoiceService;

    @Test
    public void testGenerateInvoice() {
        Invoice invoice = generateInvoice();
        String viewInvoiceLink = invoiceService.generateTransactionLink(TransactionType.AIRTIME, invoice.getId());
        assertNotNull(viewInvoiceLink);
    }

    Invoice generateInvoice() {
        CreateInvoiceRequest invoice = new CreateInvoiceRequest();
        Product product = new Product("Detergent", BigDecimal.valueOf(1000));
        invoice.setItems(List.of(new InvoiceItem(product, 10)));
        return invoiceService.generateInvoice(invoice);
    }

    @Test
    public void testGenerateTransactionLink() {
        Invoice invoice = generateInvoice();
        String viewInvoiceLink = invoiceService.generateTransactionLink(TransactionType.AIRTIME, invoice.getId());
        assertNotNull(viewInvoiceLink);
    }

    @Test
    public void testGetInvoice() {
        Invoice invoice = generateInvoice();
        Invoice existingInvoice = invoiceService.getInvoice(invoice.getId());
        assertNotNull(existingInvoice);
    }

    @Test
    public void testGetNonExistingInvoice() {
        Invoice existingInvoice = invoiceService.getInvoice("123456");
        assertNull(existingInvoice);
    }
}
