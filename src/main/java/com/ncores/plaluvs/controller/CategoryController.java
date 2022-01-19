package com.ncores.plaluvs.controller;

import com.ncores.plaluvs.controller.category.dto.CategoryResponseDto;
import com.ncores.plaluvs.domain.dto.PagingSimpleResponseDto;
import com.ncores.plaluvs.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<?> findCategory(){
        List<CategoryResponseDto> categoryResponseDto = categoryService.findAll();

        return new ResponseEntity<>(new PagingSimpleResponseDto<>(categoryResponseDto.size(), categoryResponseDto),HttpStatus.OK);
    }
}
