package com.pharmacy.service;


import com.pharmacy.model.SaleDetail;
import com.pharmacy.repository.InvoiceRepository;
import com.pharmacy.repository.ProductRepository;
import com.pharmacy.repository.SaleDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleDetailService {

    private final SaleDetailRepository saleDetailRepository;
    private final ProductRepository productRepository;
    private final InvoiceRepository invoiceRepository;

    @Autowired
    public SaleDetailService(SaleDetailRepository saleDetailRepository,
                             ProductRepository productRepository,
                             InvoiceRepository invoiceRepository) {
        this.saleDetailRepository = saleDetailRepository;
        this.productRepository = productRepository;
        this.invoiceRepository = invoiceRepository;
    }

    public List<SaleDetail> getAllSaleDetails() {
        return saleDetailRepository.findAll();
    }

    public Optional<SaleDetail> getSaleDetailById(Long id) {
        return saleDetailRepository.findById(id);
    }

    public SaleDetail createSaleDetail(SaleDetail saleDetail) {
        // Ensure associated Product exists
        if (saleDetail.getProduct() != null && saleDetail.getProduct().getId() != null) {
            productRepository.findById(saleDetail.getProduct().getId()).ifPresent(saleDetail::setProduct);
        }
        // Ensure associated Invoice exists
        if (saleDetail.getInvoice() != null && saleDetail.getInvoice().getId() != null) {
            invoiceRepository.findById(saleDetail.getInvoice().getId()).ifPresent(saleDetail::setInvoice);
        }
        return saleDetailRepository.save(saleDetail);
    }

    public Optional<SaleDetail> updateSaleDetail(Long id, SaleDetail saleDetailDetails) {
        return saleDetailRepository.findById(id).map(saleDetail -> {
            // Update product if provided and exists
            if (saleDetailDetails.getProduct() != null && saleDetailDetails.getProduct().getId() != null) {
                productRepository.findById(saleDetailDetails.getProduct().getId())
                        .ifPresent(saleDetail::setProduct);
            } else if (saleDetailDetails.getProduct() == null) {
                saleDetail.setProduct(null);
            }

            saleDetail.setQuantity(saleDetailDetails.getQuantity());

            // Update invoice if provided and exists
            if (saleDetailDetails.getInvoice() != null && saleDetailDetails.getInvoice().getId() != null) {
                invoiceRepository.findById(saleDetailDetails.getInvoice().getId())
                        .ifPresent(saleDetail::setInvoice);
            } else if (saleDetailDetails.getInvoice() == null) {
                saleDetail.setInvoice(null);
            }
            return saleDetailRepository.save(saleDetail);
        });
    }

    public boolean deleteSaleDetail(Long id) {
        if (saleDetailRepository.existsById(id)) {
            saleDetailRepository.deleteById(id);
            return true;
        }
        return false;
    }
}