package com.filmland.assestment.controller;

import com.filmland.assestment.dto.CategoryResponseDto;
import com.filmland.assestment.facade.SessionFacade;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final SessionFacade sessionFacade;

    @GetMapping("/{username}")
    public ResponseEntity<CategoryResponseDto> getCategories(@PathVariable @NotNull String username) {

        CategoryResponseDto availableCategoryService = sessionFacade.getAllCategories(username);
        return ResponseEntity.ok(availableCategoryService);


    }
}
