package com.pharmacy.service;

import com.pharmacy.model.PurchaseDetail;
import com.pharmacy.repository.PurchaseDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseDetailService {

    private final PurchaseDetailRepository purchaseDetailRepository;

    @Autowired
    public PurchaseDetailService(PurchaseDetailRepository purchaseDetailRepository) {
        this.purchaseDetailRepository = purchaseDetailRepository;
    }

    public List<PurchaseDetail> getAllPurchaseDetails() {
        return purchaseDetailRepository.findAll();
    }

    public Optional<PurchaseDetail> getPurchaseDetailById(Long id) {
        return purchaseDetailRepository.findById(id);
    }

    public PurchaseDetail createPurchaseDetail(PurchaseDetail purchaseDetail) {
        return purchaseDetailRepository.save(purchaseDetail);
    }

    public Optional<PurchaseDetail> updatePurchaseDetail(Long id, PurchaseDetail purchaseDetailDetails) {
        return purchaseDetailRepository.findById(id).map(purchaseDetail -> {
            return purchaseDetailRepository.save(purchaseDetailDetails);
        });
    }

    public boolean deletePurchaseDetail(Long id) {
        if (purchaseDetailRepository.existsById(id)) {
            purchaseDetailRepository.deleteById(id);
            return true;
        }
        return false;
    }
}