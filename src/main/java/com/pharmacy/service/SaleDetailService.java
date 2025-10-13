package com.pharmacy.service;

import com.pharmacy.model.SaleDetail;
import com.pharmacy.repository.SaleDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleDetailService {

    private final SaleDetailRepository saleDetailRepository;

    @Autowired
    public SaleDetailService(SaleDetailRepository saleDetailRepository) {
        this.saleDetailRepository = saleDetailRepository;
    }

    public List<SaleDetail> getAllSaleDetails() {
        return saleDetailRepository.findAll();
    }

    public Optional<SaleDetail> getSaleDetailById(Long id) {
        return saleDetailRepository.findById(id);
    }

    public SaleDetail createSaleDetail(SaleDetail saleDetail) {
        return saleDetailRepository.save(saleDetail);
    }

    public Optional<SaleDetail> updateSaleDetail(Long id, SaleDetail saleDetailDetails) {
        return saleDetailRepository.findById(id).map(saleDetail -> {
            return saleDetailRepository.save(saleDetailDetails);
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