package com.ncores.plaluvs.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.Query;

@Getter
@Setter
@NoArgsConstructor
public class ElementsDto {
    private Long id;
    private String korName;
    private String img;
    private String level;


    @QueryProjection
    public ElementsDto(Long id, String korName, String img, String level) {
        this.id = id;
        this.korName = korName;
        this.img = img;
        this.level = level;
    }

    @QueryProjection
    public ElementsDto(Long id, String korName, String level) {
        this.id = id;
        this.korName = korName;
        this.level = level;
    }
}
