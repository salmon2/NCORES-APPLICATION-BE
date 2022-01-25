package com.ncores.plaluvs.controller.cometic.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SimpleCosmeticDto {
    private Long id;
    private String img;
    private String name;
    private Boolean likeCheck;
    private String naverUrl;
    private String brandName;
    private Long categoryId;

    @QueryProjection
    public SimpleCosmeticDto(Long id, String img, String name, Boolean likeCheck, String brandName, Long categoryId) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.likeCheck = likeCheck;
        this.brandName = brandName;
        this.categoryId = categoryId;
    }

    @QueryProjection
    public SimpleCosmeticDto(Long id, String img, String name, Boolean likeCheck, Long categoryId) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.likeCheck = likeCheck;
        this.categoryId = categoryId;
    }

    @QueryProjection
    public SimpleCosmeticDto(Long id, String img, String name, Boolean likeCheck) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.likeCheck = likeCheck;
    }

    @QueryProjection
    public SimpleCosmeticDto(Long id, String img, String name, Boolean likeCheck, String naverUrl) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.likeCheck = likeCheck;
        this.naverUrl = naverUrl;
    }
}
