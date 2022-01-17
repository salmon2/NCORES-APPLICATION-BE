package com.ncores.plaluvs.controller.cometic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetailCosmeticDto {
    private Long id;
    private String img;
    private String brand;
    private String name;
    private String price;
    private Boolean likeCheck;
}
