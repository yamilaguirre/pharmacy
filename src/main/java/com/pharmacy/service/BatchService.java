package com.pharmacy.service;

import com.pharmacy.model.Batch;
import com.pharmacy.repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BatchService {

    private final BatchRepository batchRepository;

    @Autowired
    public BatchService(BatchRepository batchRepository) {
        this.batchRepository = batchRepository;
    }

    public List<Batch> getAllBatches() {
        return batchRepository.findAll();
    }

    public Optional<Batch> getBatchById(Long id) {
        return batchRepository.findById(id);
    }

    public Batch createBatch(Batch batch) {
        return batchRepository.save(batch);
    }

    public Optional<Batch> updateBatch(Long id, Batch batchDetails) {
        return batchRepository.findById(id).map(batch -> {
            return batchRepository.save(batchDetails);
        });
    }

    public boolean deleteBatch(Long id) {
        if (batchRepository.existsById(id)) {
            batchRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Batch> getBatchesByPurchaseDetailId(Long purchaseDetailId) {
        return batchRepository.findByPurchaseDetailId(purchaseDetailId);
    }
}
