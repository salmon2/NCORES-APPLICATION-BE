package com.ncores.plaluvs.controller.cometic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CosmeticElementsResponseDto<T> {
    private int size;
    private Long currentPage;
    private Long maxPage;
    private String elements;
    private T data;
}
