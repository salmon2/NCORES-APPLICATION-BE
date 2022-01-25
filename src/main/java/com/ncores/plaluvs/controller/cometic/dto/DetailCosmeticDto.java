package com.ncores.plaluvs.controller.cometic.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DetailCosmeticDto {
    private Long id;
    private String img;
    private String brand;
    private String name;
    private String price;
    private Boolean likeCheck;
    private String naverUrl;
    private Long CategoryId;

    @QueryProjection
    public DetailCosmeticDto(Long id, String img, String brand, String name, String price, Boolean likeCheck) {
        this.id = id;
        this.img = img;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.likeCheck = likeCheck;
    }
    @QueryProjection
    public DetailCosmeticDto(Long id, String img, String brand, String name, String price, Boolean likeCheck, Long categoryId) {
        this.id = id;
        this.img = img;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.likeCheck = likeCheck;
        CategoryId = categoryId;
    }
}
