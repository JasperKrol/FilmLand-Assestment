package com.filmland.assestment.repository;

import com.filmland.assestment.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {


    Optional<Category> findCategoryByName(String requestedCategory);
}
