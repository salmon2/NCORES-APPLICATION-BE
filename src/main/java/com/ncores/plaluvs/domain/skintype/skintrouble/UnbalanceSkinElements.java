package com.ncores.plaluvs.domain.skintype.skintrouble;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public enum UnbalanceSkinElements {
    //수화/보습 효과
    뷰글추출물("뷰글추출물")
    ,덱스판테놀("덱스판테놀")
    ,다이메티콘("다이메티콘")
    ,달맞이꽃오일("달맞이꽃오일")
    ,보리지씨오일("보리지씨오일")
    ,글리세린("글리세린")
    ,카놀라오일("카놀라오일")
    ,퀸즈랜드넛오일("퀸즈랜드넛오일")
    ,콜레스테롤("콜레스테롤")
    ,올리브오일("올리브오일")
    ,잇꽃씨오일("잇꽃씨오일")
    ,콜로이달오트밀("콜로이달오트밀")
    ,시어버터("시어버터");

    private String name;

}
