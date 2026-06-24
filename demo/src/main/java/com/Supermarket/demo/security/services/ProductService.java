package com.Supermarket.demo.security.services;

import com.Supermarket.demo.dto.PagedResponse;
import com.Supermarket.demo.dto.ProductRequest;
import com.Supermarket.demo.dto.ProductResponse;
import com.Supermarket.demo.entities.Category;
import com.Supermarket.demo.entities.Product;
import com.Supermarket.demo.entities.Supplier;
import com.Supermarket.demo.enums.ProductExpirationStatus;
import com.Supermarket.demo.exceptions.ResourceNotFoundException;
import com.Supermarket.demo.repositories.CategoryRepository;
import com.Supermarket.demo.repositories.ProductRepository;
import com.Supermarket.demo.repositories.SupplierRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;

    public ProductService(ProductRepository repository,
                          CategoryRepository categoryRepository,
                          SupplierRepository supplierRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
    }

    public PagedResponse<ProductResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return PagedResponse.from(
                repository.findAll(pageable).map(ProductResponse::from)
        );
    }

    public ProductResponse getById(Long id) {
        return ProductResponse.from(getProductOrThrow(id));
    }

    public List<ProductResponse> search(Long categoryId, Long supplierId) {
        return repository.search(categoryId, supplierId)
                .stream()
                .map(ProductResponse::from)
                .toList();
    }

    public List<ProductResponse> getByExpirationStatus(ProductExpirationStatus status) {
        return repository.findByExpirationStatus(status)
                .stream()
                .map(ProductResponse::from)
                .toList();
    }

    @Transactional
    public ProductResponse create(ProductRequest dto) {
        Product product = new Product();
        applyRequest(dto, product);
        return ProductResponse.from(repository.save(product));
    }

    @Transactional
    public ProductResponse update(Long id, ProductRequest dto) {
        Product product = getProductOrThrow(id);
        applyRequest(dto, product);
        return ProductResponse.from(repository.save(product));
    }

    @Transactional
    public void delete(Long id) {
        Product product = getProductOrThrow(id);
        repository.delete(product);
    }

    private Product getProductOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PRODUCT_NOT_FOUND"));
    }

    private void applyRequest(ProductRequest dto, Product product) {
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        product.setExpirationStatus(dto.getExpirationStatus());

        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("CATEGORY_NOT_FOUND"));
            product.setCategory(category);
        } else {
            product.setCategory(null);
        }

        if (dto.getSupplierId() != null) {
            Supplier supplier = supplierRepository.findById(dto.getSupplierId())
                    .orElseThrow(() -> new ResourceNotFoundException("SUPPLIER_NOT_FOUND"));
            product.setSupplier(supplier);
        } else {
            product.setSupplier(null);
        }
    }
}
