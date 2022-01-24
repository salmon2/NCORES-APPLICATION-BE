package com.ncores.plaluvs.controller.skin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class skinStatusBoumanResponseDto {
    private List<ScoreData> aquaScore = new ArrayList<>();
    private List<ScoreData> oilScore = new ArrayList<>();
    private  List<ScoreData> sensitiveScore= new ArrayList<>();
    private  List<ScoreData> pigmentScore = new ArrayList<>();
    private List<ScoreData> winkleScore =new ArrayList<>();
}
