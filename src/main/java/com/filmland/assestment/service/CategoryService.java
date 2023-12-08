package com.filmland.assestment.service;

import com.filmland.assestment.dto.CategoryDto;
import com.filmland.assestment.dto.CategoryResponseDto;
import com.filmland.assestment.entity.Category;
import com.filmland.assestment.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryResponseDto getAllCategories() {

        CategoryResponseDto response = new CategoryResponseDto();

        List<Category> allCategories = categoryRepository.findAll();

        List<CategoryDto> toDto = allCategories.stream()
                .map(category -> new CategoryDto(category.getName(), category.getAvailableContent(),category.getPrice())).collect(Collectors.toList());

        response.setAvailableCategories(toDto);


        return response;
    }
}
