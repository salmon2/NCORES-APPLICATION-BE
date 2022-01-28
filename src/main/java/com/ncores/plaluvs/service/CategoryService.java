package com.ncores.plaluvs.service;

import com.ncores.plaluvs.controller.category.dto.CategoryResponseDto;
import com.ncores.plaluvs.domain.Category;
import com.ncores.plaluvs.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public  List<CategoryResponseDto> findAll() {

        List<Category> all = categoryRepository.findAll();
        List<CategoryResponseDto> result = new ArrayList<>();

        for (Category category : all) {
            CategoryResponseDto categoryResponseDto = new CategoryResponseDto(category.getId(), category.getCategoryLarge());
            result.add(categoryResponseDto);
        }

        return result;
    }
}
