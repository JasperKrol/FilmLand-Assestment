package com.filmland.assestment.controller;

import com.filmland.assestment.dto.CategoryResponseDto;
import com.filmland.assestment.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<CategoryResponseDto> getCategories() {

        // TODO: 08-12-2023 username to param and get all categories, except subscribed
        // TODO: 08-12-2023 also get the subscribed ones from user

        CategoryResponseDto availableCategoryService = categoryService.getAllCategories();

        return ResponseEntity.ok(availableCategoryService);
    }
}
