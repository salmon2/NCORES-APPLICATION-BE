package com.ncores.plaluvs.controller.skin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SkinStatusResponseDto {
    private String totalScore;
    private String content;
    private Long oilIndicate;
    private Long dry;
    private Long sensitivity;
    private Long pigment;
    private Long winkle;
    private List<SkinElementsDto> elementList = new ArrayList<>();
}
