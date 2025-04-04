package com.edusync.service;

import com.edusync.entity.Category;
import com.edusync.exception.CategoryException;
import com.edusync.repo.CategoryRepository;
import com.edusync.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse createCategory(String name) {
        Category category = Category
                .builder()
                .name(name)
                .build();
        if(categoryRepository.findByName(name).isPresent()) {
            throw new CategoryException("Category with name: " + name + " already present");
        }
        Category savedCategory = categoryRepository.save(category);
        return CategoryResponse
                .builder()
                .id(savedCategory.getId())
                .name(savedCategory.getName())
                .build();
    }

    @Override
    public CategoryResponse updateCategory(Integer categoryId, String name) {
        Category oldCategory = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new CategoryException("Category with id: " + categoryId + " not found"));
        oldCategory.setName(name);
        Category savedCategory = categoryRepository.save(oldCategory);
        return CategoryResponse
                .builder()
                .id(savedCategory.getId())
                .name(savedCategory.getName())
                .build();
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new CategoryException("Category with id: " + categoryId + " not found. So cannot delete"));
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public CategoryResponse getCategoryById(Integer categoryId) {
        Category savedCategory = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new CategoryException("Category with id: " + categoryId + " not found"));
        return CategoryResponse
                .builder()
                .id(savedCategory.getId())
                .name(savedCategory.getName())
                .build();
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        List<Category> savedCategories = categoryRepository.findAll();
        return savedCategories
                .stream()
                .map(category -> CategoryResponse
                        .builder()
                        .id(category.getId())
                        .name(category.getName())
                        .build())
                .toList();
    }

    @Override
    public List<Category> getAllCategoriesByIds(List<Integer> ids) {
        return ids
                .stream()
                .map(id -> categoryRepository
                        .findById(id)
                        .orElseThrow(() -> new CategoryException("category with id: " + id + " not found")))
                .toList();
    }

    public Category getCategoryUnsafeById(Integer categoryId) {
        return categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new CategoryException("category with id: " + categoryId + " not found"));
    }
}
