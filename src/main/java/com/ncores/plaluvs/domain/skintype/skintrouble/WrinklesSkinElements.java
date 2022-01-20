package com.ncores.plaluvs.domain.skintype.skintrouble;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public enum WrinklesSkinElements {
    //주름 개선
    락틱애씨드("락틱애씨드")
    ,시트릭애씨드("시트릭애씨드")
    ,파이틱애씨드("파이틱애씨드")
    ,글루코노락톤("글루코노락톤")

    //주름 예방
    ,바질추출물("바질추출물")
    ,인삼("인삼")
    ,홍삼("홍삼")
    ,카페인("카페인")
    ,포도씨추출물("포도씨추출물")
    ,녹차("녹차")
    ,당근추출물("당근추출물")
    ,라이코펜("라이코펜")
    ,유비퀴논("유비퀴논")
    ,피톨("피톨")
    ,석류추출물("석류추출물")
    ,커큐민("커큐민")
    ,페룰릭애씨드("페룰릭애씨드")
    ,로즈마리("로즈마리")
    ,제니스테인("제니스테인")
    ,생강("생강");

    private String name;

}
