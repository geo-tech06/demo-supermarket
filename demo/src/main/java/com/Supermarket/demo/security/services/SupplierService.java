package com.Supermarket.demo.security.services;

import com.Supermarket.demo.dto.SupplierRequest;
import com.Supermarket.demo.dto.SupplierResponse;
import com.Supermarket.demo.entities.Supplier;
import com.Supermarket.demo.exceptions.ResourceNotFoundException;
import com.Supermarket.demo.repositories.SupplierRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository repository;

    public SupplierService(SupplierRepository repository) {
        this.repository = repository;
    }

    public List<SupplierResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(SupplierResponse::from)
                .toList();
    }

    public SupplierResponse getById(Long id) {
        return SupplierResponse.from(getSupplierOrThrow(id));
    }

    @Transactional
    public SupplierResponse create(SupplierRequest dto) {
        Supplier supplier = new Supplier();
        applyRequest(dto, supplier);
        return SupplierResponse.from(repository.save(supplier));
    }

    @Transactional
    public SupplierResponse update(Long id, SupplierRequest dto) {
        Supplier supplier = getSupplierOrThrow(id);
        applyRequest(dto, supplier);
        return SupplierResponse.from(repository.save(supplier));
    }

    @Transactional
    public void delete(Long id) {
        Supplier supplier = getSupplierOrThrow(id);
        repository.delete(supplier);
    }

    private Supplier getSupplierOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SUPPLIER_NOT_FOUND"));
    }

    private void applyRequest(SupplierRequest dto, Supplier supplier) {
        supplier.setName(dto.getName());
        supplier.setContactEmail(dto.getContactEmail());
    }
}
