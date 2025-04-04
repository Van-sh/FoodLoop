package com.edusync.service;

import com.edusync.entity.Category;
import com.edusync.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse createCategory(String name);
    CategoryResponse updateCategory(Integer categoryId, String name);
    void deleteCategory(Integer categoryId);
    CategoryResponse getCategoryById(Integer categoryId);
    List<CategoryResponse> getAllCategories();
    List<Category> getAllCategoriesByIds(List<Integer> ids);
}
