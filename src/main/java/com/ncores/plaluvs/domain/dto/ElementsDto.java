package com.ncores.plaluvs.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ElementsDto {
    private Long id;
    private String korName;
    private String img;
    private String level;
    private Boolean likeCheck;


    @QueryProjection
    public ElementsDto(Long id, String korName, String level) {
        this.id = id;
        this.korName = korName;
        this.level = level;
    }

    @QueryProjection
    public ElementsDto(Long id, String korName, String level, Boolean likeCheck) {
        this.id = id;
        this.korName = korName;
        this.level = level;
        this.likeCheck = likeCheck;
    }
}
