package com.pharmacy.controller;
import com.pharmacy.model.PurchaseDetail;
import com.pharmacy.service.PurchaseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/v1/purchase-details")
public class PurchaseDetailController {

    private final PurchaseDetailService purchaseDetailService;

    @Autowired
    public PurchaseDetailController(PurchaseDetailService purchaseDetailService) {
        this.purchaseDetailService = purchaseDetailService;
    }

    @GetMapping
    public ResponseEntity<List<PurchaseDetail>> getAllPurchaseDetails() {
        List<PurchaseDetail> purchaseDetails = purchaseDetailService.getAllPurchaseDetails();
        return ResponseEntity.ok(purchaseDetails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseDetail> getPurchaseDetailById(@PathVariable Long id) {
        Optional<PurchaseDetail> purchaseDetail = purchaseDetailService.getPurchaseDetailById(id);
        return purchaseDetail.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PurchaseDetail> createPurchaseDetail(@RequestBody PurchaseDetail purchaseDetail) {
        PurchaseDetail createdPurchaseDetail = purchaseDetailService.createPurchaseDetail(purchaseDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPurchaseDetail);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseDetail> updatePurchaseDetail(@PathVariable Long id, @RequestBody PurchaseDetail purchaseDetailDetails) {
        Optional<PurchaseDetail> updatedPurchaseDetail = purchaseDetailService.updatePurchaseDetail(id, purchaseDetailDetails);
        return updatedPurchaseDetail.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchaseDetail(@PathVariable Long id) {
        if (purchaseDetailService.deletePurchaseDetail(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
