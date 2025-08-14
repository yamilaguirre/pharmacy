package com.pharmacy.controller;
import com.pharmacy.model.SaleDetail;
import com.pharmacy.service.SaleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/v1/sale-details")
public class SaleDetailController {

    private final SaleDetailService saleDetailService;

    @Autowired
    public SaleDetailController(SaleDetailService saleDetailService) {
        this.saleDetailService = saleDetailService;
    }

    @GetMapping
    public ResponseEntity<List<SaleDetail>> getAllSaleDetails() {
        List<SaleDetail> saleDetails = saleDetailService.getAllSaleDetails();
        return ResponseEntity.ok(saleDetails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDetail> getSaleDetailById(@PathVariable Long id) {
        Optional<SaleDetail> saleDetail = saleDetailService.getSaleDetailById(id);
        return saleDetail.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SaleDetail> createSaleDetail(@RequestBody SaleDetail saleDetail) {
        SaleDetail createdSaleDetail = saleDetailService.createSaleDetail(saleDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSaleDetail);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleDetail> updateSaleDetail(@PathVariable Long id, @RequestBody SaleDetail saleDetailDetails) {
        Optional<SaleDetail> updatedSaleDetail = saleDetailService.updateSaleDetail(id, saleDetailDetails);
        return updatedSaleDetail.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSaleDetail(@PathVariable Long id) {
        if (saleDetailService.deleteSaleDetail(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}