package com.edusync.controller;

import com.edusync.entity.Category;
import com.edusync.response.CategoryResponse;
import com.edusync.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/admin/create")
    public ResponseEntity<CategoryResponse> createCategory(@RequestParam String name) {
        return new ResponseEntity<>(categoryService.createCategory(name), HttpStatus.CREATED);
    }

    @PutMapping("/admin/update/{categoryId}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Integer categoryId,
                                                           @RequestParam String name) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryId, name));
    }

    @DeleteMapping("/admin/delete/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("Deleted Category with ID: " + categoryId);
    }

    @GetMapping("/user/{categoryId}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable Integer categoryId) {
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }

    @GetMapping("/user")
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/user/list")
    public ResponseEntity<List<Category>> getAllCategories(
            @RequestHeader List<Integer> ids
    ) {
        return ResponseEntity.ok(categoryService.getAllCategoriesByIds(ids));
    }


}


























