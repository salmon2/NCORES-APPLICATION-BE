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
    private String img;

    @QueryProjection
    public CosmeticDto(Long id, String korName, String img) {
        this.id = id;
        this.korName = korName;
        this.img = img;
    }
}
