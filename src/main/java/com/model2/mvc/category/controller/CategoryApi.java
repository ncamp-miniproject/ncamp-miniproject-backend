package com.model2.mvc.category.controller;

import com.model2.mvc.category.domain.Category;
import com.model2.mvc.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryApi {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Void> addCategory(@RequestParam("categoryName") String categoryName) {
        this.categoryService.insertCategory(categoryName);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{categoryNo}")
    public ResponseEntity<Void> updateCategory(@PathVariable("categoryNo") int categoryNo,
                                               @RequestParam("newCategoryName") String newCategoryName) {
        return this.categoryService.updateCategory(categoryNo, newCategoryName)
               ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
               : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return new ResponseEntity<>(this.categoryService.getCategoryList(), HttpStatus.OK);
    }
}
