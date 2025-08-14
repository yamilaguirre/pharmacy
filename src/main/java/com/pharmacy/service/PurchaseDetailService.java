package com.pharmacy.service;

import com.pharmacy.model.PurchaseDetail;
import com.pharmacy.repository.PurchaseDetailRepository;
import com.pharmacy.repository.SupplierRepository;
import com.pharmacy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseDetailService {

    private final PurchaseDetailRepository purchaseDetailRepository;
    private final SupplierRepository supplierRepository;
    private final UserRepository userRepository;

    @Autowired
    public PurchaseDetailService(PurchaseDetailRepository purchaseDetailRepository,
                                 SupplierRepository supplierRepository,
                                 UserRepository userRepository) {
        this.purchaseDetailRepository = purchaseDetailRepository;
        this.supplierRepository = supplierRepository;
        this.userRepository = userRepository;
    }

    public List<PurchaseDetail> getAllPurchaseDetails() {
        return purchaseDetailRepository.findAll();
    }

    public Optional<PurchaseDetail> getPurchaseDetailById(Long id) {
        return purchaseDetailRepository.findById(id);
    }

    public PurchaseDetail createPurchaseDetail(PurchaseDetail purchaseDetail) {
        // Ensure associated Supplier exists
        if (purchaseDetail.getSupplier() != null && purchaseDetail.getSupplier().getId() != null) {
            supplierRepository.findById(purchaseDetail.getSupplier().getId()).ifPresent(purchaseDetail::setSupplier);
        }
        // Ensure associated User exists
        if (purchaseDetail.getUser() != null && purchaseDetail.getUser().getId() != null) {
            userRepository.findById(purchaseDetail.getUser().getId()).ifPresent(purchaseDetail::setUser);
        }
        return purchaseDetailRepository.save(purchaseDetail);
    }

    public Optional<PurchaseDetail> updatePurchaseDetail(Long id, PurchaseDetail purchaseDetailDetails) {
        return purchaseDetailRepository.findById(id).map(purchaseDetail -> {
            purchaseDetail.setTotalAmount(purchaseDetailDetails.getTotalAmount());
            purchaseDetail.setPurchaseDate(purchaseDetailDetails.getPurchaseDate());

            // Update supplier if provided and exists
            if (purchaseDetailDetails.getSupplier() != null && purchaseDetailDetails.getSupplier().getId() != null) {
                supplierRepository.findById(purchaseDetailDetails.getSupplier().getId())
                        .ifPresent(purchaseDetail::setSupplier);
            } else if (purchaseDetailDetails.getSupplier() == null) {
                purchaseDetail.setSupplier(null);
            }

            // Update user if provided and exists
            if (purchaseDetailDetails.getUser() != null && purchaseDetailDetails.getUser().getId() != null) {
                userRepository.findById(purchaseDetailDetails.getUser().getId())
                        .ifPresent(purchaseDetail::setUser);
            } else if (purchaseDetailDetails.getUser() == null) {
                purchaseDetail.setUser(null);
            }
            return purchaseDetailRepository.save(purchaseDetail);
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