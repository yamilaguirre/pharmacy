package com.pharmacy.service;

import com.pharmacy.model.Supplier;
import com.pharmacy.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> getAllSuppliers(){
        return supplierRepository.findAll();
    }

    public Optional<Supplier> getSupplierById(Long id){
        return supplierRepository.findById(id);
    }

    public Supplier createSupplier(Supplier supplier){return supplierRepository.save(supplier);}

    public Supplier updateSupplier(Long id, Supplier supplierDetails){
        return supplierRepository.findById(id).map(supplier->{
            supplier.setName(supplierDetails.getName());
            supplier.setIdentification_number(supplierDetails.getIdentification_number());
            supplier.setAddress(supplierDetails.getAddress());
            supplier.setPhone_number(supplierDetails.getPhone_number());
            return supplierRepository.save(supplier);
        }).orElse(null);
    }

    public boolean deleteSupplier(Long id){
        if (supplierRepository.existsById(id)){
            supplierRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
