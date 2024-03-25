/**
 * Created By Chivo
 */


package com.project.Link.generator.service;

import com.project.Link.generator.model.CreateInvoiceRequest;
import com.project.Link.generator.model.Invoice;
import com.project.Link.generator.model.InvoiceItem;
import com.project.Link.generator.model.TransactionType;
import com.project.Link.generator.repository.InvoiceItemRepository;
import com.project.Link.generator.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    @Value("${transaction.link.url:https://example.com/transactions}")
    private String transactionLinkUrl;

    private final InvoiceRepository invoiceRepository;
    private final InvoiceItemRepository invoiceItemRepository;

    public Invoice generateInvoice(CreateInvoiceRequest createInvoiceRequest) {
        Invoice invoice = new Invoice();
        List<InvoiceItem> unsavedInvoiceItems = new ArrayList<>();
        createInvoiceRequest
                .getItems()
                .forEach(item -> unsavedInvoiceItems.add(new InvoiceItem(item.getProduct(), item.getQuantity())));
        invoice.setItems(unsavedInvoiceItems);
        return invoiceRepository.save(invoice);
    }

    public String generateTransactionLink(TransactionType type, String invoiceId) {
        Invoice invoice = getInvoice(invoiceId);
        if(invoice != null)
            return String.format("%s?type=%s&invoiceId=%s", transactionLinkUrl, type.name(), invoiceId);
        return null;
    }

    public Invoice getInvoice(String invoiceId) {
        return invoiceRepository.findById(invoiceId).orElse(null);
    }
}
