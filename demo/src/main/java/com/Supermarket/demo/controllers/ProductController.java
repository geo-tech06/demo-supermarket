package com.Supermarket.demo.controllers;

import com.Supermarket.demo.dto.PagedResponse;
import com.Supermarket.demo.dto.ProductRequest;
import com.Supermarket.demo.dto.ProductResponse;
import com.Supermarket.demo.enums.ProductExpirationStatus;
import com.Supermarket.demo.security.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public PagedResponse<ProductResponse> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return service.getAll(page, size);
    }

    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/search")
    public List<ProductResponse> search(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long supplierId) {
        return service.search(categoryId, supplierId);
    }

    @GetMapping("/expiration/{status}")
    public List<ProductResponse> getByExpirationStatus(@PathVariable ProductExpirationStatus status) {
        return service.getByExpirationStatus(status);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(@Valid @RequestBody ProductRequest dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ProductResponse update(@PathVariable Long id, @Valid @RequestBody ProductRequest dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
