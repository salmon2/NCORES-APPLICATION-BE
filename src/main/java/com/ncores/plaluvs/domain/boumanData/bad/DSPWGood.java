package com.ncores.plaluvs.domain.boumanData.bad;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DSPWGood {

    //여드름/홍조 증가
    육계잎오일("육계잎오일")
    ,육계전초오일("육계전초오일")
    ,코코넛야자오일("코코넛야자오일")
    ,아이소프로필아이소스테아레이트("아이소프로필아이소스테아레이트")
    ,아이소프로필미리스테이트("아이소프로필미리스테이트")
    ,페퍼민트오일("페퍼민트오일")

    //홍조 증가 (홍조 증상이 있는 경우)
    ,락틱애씨드("락틱애씨드")
    ,글라이콜릭애씨드("글라이콜릭애씨드")
    ,글루코노락톤("글루코노락톤")
    ,파이틱애씨드("파이틱애씨드")
    ,레티놀("레티놀")
    ,레티닐팔미테이트("레티닐팔미테이트")
    ,살리실릭애씨드("살리실릭애씨드")
    ,아스코빅애씨드("아스코빅애씨드");

    private  String name;

}
