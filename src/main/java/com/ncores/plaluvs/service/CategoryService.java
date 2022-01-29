package com.ncores.plaluvs.service;

import com.ncores.plaluvs.controller.category.dto.CategoryEnum;
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

//        List<Category> all = categoryRepository.findAll();
        List<CategoryResponseDto> result = new ArrayList<>();

        result.add(new CategoryResponseDto(617L, "에센스토너"));
        result.add(new CategoryResponseDto(687L, "로션/에멀젼"));
        result.add(new CategoryResponseDto(582L, "에센스/세럼"));
        result.add(new CategoryResponseDto(608L, "크림"));
        result.add(new CategoryResponseDto(757L, "클렌징워터"));
        result.add(new CategoryResponseDto(23L, "립케어"));
        result.add(new CategoryResponseDto(679L, "선크림"));

        return result;
    }
}
