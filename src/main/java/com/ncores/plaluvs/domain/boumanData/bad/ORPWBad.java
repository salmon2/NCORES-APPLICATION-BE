package com.ncores.plaluvs.domain.boumanData.bad;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ORPWBad {
//    색소/검은 반점 증가
라케모사승마("라케모사승마")
,라케모사승마뿌리추출물("라케모사승마뿌리추출물")
,체이스트트리추출물("체이스트트리추출물")
,호프("호프")
,붉은토끼풀추출물("붉은토끼풀추출물")
,콩("콩")
,돌콩("돌콩")


//    피비 과다 분비
    ,미네랄오일("미네랄오일")
    ,코코넛야자오일("코코넛야자오일")

  //  피지 과다 분비
    ,페트롤라툼("페트롤라툼")


    //피부톤 다운
    ,셀러리추출물("셀러리추출물")
    ,라임추출물("라임추출물")
    ,파슬리추출물("파슬리추출물")
    ,무화과추출물("무화과추출물")
    ,당근추출물("당근추출물")
    ,베르가못오일("베르가못오일");

    private String name;
}
