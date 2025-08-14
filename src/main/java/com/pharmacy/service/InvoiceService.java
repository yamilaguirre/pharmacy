package com.pharmacy.service;

import com.pharmacy.model.Invoice;
import com.pharmacy.repository.CustomerRepository;
import com.pharmacy.repository.InvoiceRepository;
import com.pharmacy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository,
                          CustomerRepository customerRepository,
                          UserRepository userRepository) {
        this.invoiceRepository = invoiceRepository;
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> getInvoiceById(Long id) {
        return invoiceRepository.findById(id);
    }

    public Invoice createInvoice(Invoice invoice) {
        // Ensure associated Customer exists
        if (invoice.getCustomer() != null && invoice.getCustomer().getId() != null) {
            customerRepository.findById(invoice.getCustomer().getId()).ifPresent(invoice::setCustomer);
        }
        // Ensure associated User exists
        if (invoice.getUser() != null && invoice.getUser().getId() != null) {
            userRepository.findById(invoice.getUser().getId()).ifPresent(invoice::setUser);
        }
        return invoiceRepository.save(invoice);
    }

    public Optional<Invoice> updateInvoice(Long id, Invoice invoiceDetails) {
        return invoiceRepository.findById(id).map(invoice -> {
            // Update customer if provided and exists
            if (invoiceDetails.getCustomer() != null && invoiceDetails.getCustomer().getId() != null) {
                customerRepository.findById(invoiceDetails.getCustomer().getId())
                        .ifPresent(invoice::setCustomer);
            } else if (invoiceDetails.getCustomer() == null) {
                invoice.setCustomer(null);
            }

            invoice.setInvoiceDate(invoiceDetails.getInvoiceDate());
            invoice.setSubtotal(invoiceDetails.getSubtotal());
            invoice.setDiscount(invoiceDetails.getDiscount());
            invoice.setTotal(invoiceDetails.getTotal());

            // Update user if provided and exists
            if (invoiceDetails.getUser() != null && invoiceDetails.getUser().getId() != null) {
                userRepository.findById(invoiceDetails.getUser().getId())
                        .ifPresent(invoice::setUser);
            } else if (invoiceDetails.getUser() == null) {
                invoice.setUser(null);
            }
            return invoiceRepository.save(invoice);
        });
    }

    public boolean deleteInvoice(Long id) {
        if (invoiceRepository.existsById(id)) {
            invoiceRepository.deleteById(id);
            return true;
        }
        return false;
    }
}