package com.ncores.plaluvs.controller.skin.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SkinElementsDto {
    private Long id;
    private String korName;
    private String img;
    private String level;
    private Boolean likeCheck;

    @QueryProjection
    public SkinElementsDto(Long id, String korName, String level, Boolean likeCheck) {
        this.id = id;
        this.korName = korName;
        this.level = level;
        this.likeCheck = likeCheck;
    }



}
