package com.ncores.plaluvs.controller.skin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SkinDailyStatusRequestDto {
    List<Long> id = new ArrayList<>();
}
