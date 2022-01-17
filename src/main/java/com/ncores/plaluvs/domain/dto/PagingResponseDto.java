package com.ncores.plaluvs.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagingResponseDto<T> {
    private int size;
    private int currentPage;
    private int maxPage;
    private T itemList;
}
