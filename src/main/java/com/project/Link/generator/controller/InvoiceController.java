/**
 * Created By Chivo
 */


package com.project.Link.generator.controller;

import com.project.Link.generator.model.CreateInvoiceRequest;
import com.project.Link.generator.model.Invoice;
import com.project.Link.generator.model.TransactionType;
import com.project.Link.generator.service.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoice")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @Operation(summary = "View Invoice")
    @GetMapping("/{invoiceId}")
    public ResponseEntity<?> viewInvoice(@PathVariable String invoiceId) {
        return ResponseEntity.ok(invoiceService.getInvoice(invoiceId));
    }

    @Operation(summary = "Generate Payment Link")
    @GetMapping("/generate-link/{invoiceId}")
    public ResponseEntity<?> generatePaymentLink(@RequestParam TransactionType transactionType, @PathVariable String invoiceId) {
        return ResponseEntity.ok(invoiceService.generateTransactionLink(transactionType, invoiceId));
    }

    @Operation(summary = "Generate Invoice")
    @PostMapping
    public ResponseEntity<Invoice> generateInvoice(@RequestBody CreateInvoiceRequest createInvoiceRequest) {
        return ResponseEntity.ok(invoiceService.generateInvoice(createInvoiceRequest));
    }
}


