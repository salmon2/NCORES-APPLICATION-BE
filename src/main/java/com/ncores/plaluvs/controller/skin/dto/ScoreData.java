package com.ncores.plaluvs.controller.skin.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScoreData {
    private String tag;
    private Long score;
    private Long rate;
    private String color;
    private LocalDateTime createdAt;


    public ScoreData(String tag, Long score, Long rate, String color) {
        this.tag = tag;
        this.score = score;
        this.rate = rate;
        this.color = color;
    }

    @QueryProjection
    public ScoreData(Long score, Long rate) {
        this.score = score;
        this.rate = rate;
    }

    @QueryProjection
    public ScoreData(Long score) {
        this.score = score;
    }

    @QueryProjection
    public ScoreData(Long score, LocalDateTime createdAt) {
        this.score = score;
        this.createdAt = createdAt;
    }
}
