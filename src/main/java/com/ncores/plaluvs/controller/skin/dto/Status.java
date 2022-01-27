package com.ncores.plaluvs.controller.skin.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Status {
    private Long score;
    private String date;
    private String type;

    public Status(Long score, String date) {
        this.score = score;
        this.date = date;
    }
}
