package com.filmland.assestment.service;

import com.filmland.assestment.dto.CategoryDto;
import com.filmland.assestment.entity.Category;
import com.filmland.assestment.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryDto> getAllCategories() {

        List<Category> allCategories = categoryRepository.findAll();

        List<CategoryDto> categoryDtoList = allCategories.stream()
                .map(category -> new CategoryDto(category.getName(), category.getAvailableContent(), category.getPrice())).collect(Collectors.toList());

        return categoryDtoList;
    }

    public Optional<Category> getCategory(String requestedCategory) {

        return categoryRepository.findCategoryByName(requestedCategory);
    }
}
