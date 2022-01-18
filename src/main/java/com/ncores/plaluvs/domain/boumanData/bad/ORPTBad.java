package com.ncores.plaluvs.domain.boumanData.bad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ORPTBad {
    //지성 피부 유발
    보리지씨오일("보리지씨오일")
    ,미네랄오일("미네랄오일")
    ,해바라기씨오일("해바라기씨오일")
    ,페트롤라툼("페트롤라툼"),

        //색소증가
    제니스테인("제니스테인")
    ,콩("콩")
    ,돌콩("돌콩")
    ,소이아이소플라본("소이아이소플라본")

        //피부톤 다운
    ,셀러리추출물("셀러리추출물")
    ,라임추출물("라임추출물")
    ,파슬리추출물("파슬리추출물")
    ,무화과추출물("무화과추출물")
    ,당근추출물("당근추출물")
    ,베르가못오일("베르가못오일");




    private String name;
}
