package com.ncores.plaluvs.controller.cometic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SimpleCosmeticResponseDto {
    private List<SimpleCosmeticDto> itemList = new ArrayList<>();
}
