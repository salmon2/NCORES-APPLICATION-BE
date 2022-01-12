package com.ncores.plaluvs.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SkinWorryRequestDto {
    List<Long> skinWorryId = new ArrayList<>();
}
