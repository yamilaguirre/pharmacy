package com.pharmacy.service;

import com.pharmacy.model.Batch;
import com.pharmacy.repository.BatchRepository;
import com.pharmacy.repository.ProductRepository;
import com.pharmacy.repository.PurchaseDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BatchService {

    private final BatchRepository batchRepository;
    private final PurchaseDetailRepository purchaseDetailRepository;
    private final ProductRepository productRepository;

    @Autowired
    public BatchService(BatchRepository batchRepository,
                        PurchaseDetailRepository purchaseDetailRepository,
                        ProductRepository productRepository) {
        this.batchRepository = batchRepository;
        this.purchaseDetailRepository = purchaseDetailRepository;
        this.productRepository = productRepository;
    }

    public List<Batch> getAllBatches() {
        return batchRepository.findAll();
    }

    public Optional<Batch> getBatchById(Long id) {
        return batchRepository.findById(id);
    }

    public Batch createBatch(Batch batch) {
        // Ensure associated PurchaseDetail exists
        if (batch.getPurchaseDetail() != null && batch.getPurchaseDetail().getId() != null) {
            purchaseDetailRepository.findById(batch.getPurchaseDetail().getId()).ifPresent(batch::setPurchaseDetail);
        }
        // Ensure associated Product exists
        if (batch.getProduct() != null && batch.getProduct().getId() != null) {
            productRepository.findById(batch.getProduct().getId()).ifPresent(batch::setProduct);
        }
        return batchRepository.save(batch);
    }

    public Optional<Batch> updateBatch(Long id, Batch batchDetails) {
        return batchRepository.findById(id).map(batch -> {
            batch.setQuantity(batchDetails.getQuantity());
            batch.setExpirationDate(batchDetails.getExpirationDate());

            // Update purchase detail if provided and exists
            if (batchDetails.getPurchaseDetail() != null && batchDetails.getPurchaseDetail().getId() != null) {
                purchaseDetailRepository.findById(batchDetails.getPurchaseDetail().getId())
                        .ifPresent(batch::setPurchaseDetail);
            } else if (batchDetails.getPurchaseDetail() == null) {
                batch.setPurchaseDetail(null);
            }

            // Update product if provided and exists
            if (batchDetails.getProduct() != null && batchDetails.getProduct().getId() != null) {
                productRepository.findById(batchDetails.getProduct().getId())
                        .ifPresent(batch::setProduct);
            } else if (batchDetails.getProduct() == null) {
                batch.setProduct(null);
            }
            return batchRepository.save(batch);
        });
    }

    public boolean deleteBatch(Long id) {
        if (batchRepository.existsById(id)) {
            batchRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
