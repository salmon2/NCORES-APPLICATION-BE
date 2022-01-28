package com.ncores.plaluvs.controller.skin.dto;

import com.ncores.plaluvs.domain.dto.PagingResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagingAveragingScoreResponseDto<T> {
    private int size;
    private String text;
    private String buttonColor;
    private Long status;
    private int currentPage;
    private int maxPage;
    private T data;
}
