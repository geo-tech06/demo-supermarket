package com.Supermarket.demo.security.services;

import com.Supermarket.demo.dto.CategoryRequest;
import com.Supermarket.demo.dto.CategoryResponse;
import com.Supermarket.demo.entities.Category;
import com.Supermarket.demo.exceptions.ResourceNotFoundException;
import com.Supermarket.demo.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<CategoryResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(CategoryResponse::from)
                .toList();
    }

    public CategoryResponse getById(Long id) {
        return CategoryResponse.from(getCategoryOrThrow(id));
    }

    @Transactional
    public CategoryResponse create(CategoryRequest dto) {
        if (repository.existsByNameIgnoreCase(dto.getName())) {
            throw new DataIntegrityViolationException("CATEGORY_NAME_ALREADY_EXISTS");
        }
        Category category = new Category();
        category.setName(dto.getName());
        return CategoryResponse.from(repository.save(category));
    }

    @Transactional
    public CategoryResponse update(Long id, CategoryRequest dto) {
        Category category = getCategoryOrThrow(id);
        if (repository.existsByNameIgnoreCaseAndIdNot(dto.getName(), id)) {
            throw new DataIntegrityViolationException("CATEGORY_NAME_ALREADY_EXISTS");
        }
        category.setName(dto.getName());
        return CategoryResponse.from(repository.save(category));
    }

    @Transactional
    public void delete(Long id) {
        Category category = getCategoryOrThrow(id);
        repository.delete(category);
    }

    private Category getCategoryOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CATEGORY_NOT_FOUND"));
    }
}
