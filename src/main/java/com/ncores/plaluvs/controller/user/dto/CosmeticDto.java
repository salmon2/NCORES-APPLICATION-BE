package com.ncores.plaluvs.controller.user.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.Query;

@Getter
@Setter
@NoArgsConstructor
public class CosmeticDto {
    private Long id;
    private String korName;
    private String brandName;
    private String img;
    private Boolean likeCheck;
    private Long categoryId;
    private String naverUrl;

    @QueryProjection
    public CosmeticDto(Long id, String korName, String img, Boolean likeCheck, Long categoryId, String brandName) {
        this.id = id;
        this.korName = korName;
        this.img = img;
        this.likeCheck = likeCheck;
        this.categoryId = categoryId;
        this.brandName = brandName;
    }

    @QueryProjection
    public CosmeticDto(Long id, String korName, String img, Boolean likeCheck, Long categoryId) {
        this.id = id;
        this.korName = korName;
        this.img = img;
        this.likeCheck = likeCheck;
        this.categoryId = categoryId;
    }
}
