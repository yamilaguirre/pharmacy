package com.pharmacy.service;

import com.pharmacy.model.Pharmacy;
import com.pharmacy.repository.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PharmacyService {

    private final PharmacyRepository pharmacyRepository;

    @Autowired
    public PharmacyService(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    public List<Pharmacy> getAllPharmacies() {
        return pharmacyRepository.findAll();
    }

    public Optional<Pharmacy> getPharmacyById(Long id) {
        return pharmacyRepository.findById(id);
    }

    public Pharmacy createPharmacy(Pharmacy pharmacy) {
        return pharmacyRepository.save(pharmacy);
    }

    public Optional<Pharmacy> updatePharmacy(Long id, Pharmacy pharmacyDetails) {
        return pharmacyRepository.findById(id).map(pharmacy -> {
            pharmacy.setTaxId(pharmacyDetails.getTaxId());
            pharmacy.setName(pharmacyDetails.getName());
            pharmacy.setAddress(pharmacyDetails.getAddress());
            pharmacy.setPhoneNumber(pharmacyDetails.getPhoneNumber());
            return pharmacyRepository.save(pharmacy);
        });
    }

    public boolean deletePharmacy(Long id) {
        if (pharmacyRepository.existsById(id)) {
            pharmacyRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
