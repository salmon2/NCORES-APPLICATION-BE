package com.ncores.plaluvs.controller.skin.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.Query;

@Getter
@Setter
@NoArgsConstructor
public class SkinStatusRecordResponseDto {
    private String date;
    private String score;

    @QueryProjection
    public SkinStatusRecordResponseDto(String date, String score) {
        this.date = date;
        this.score = score;
    }
}
