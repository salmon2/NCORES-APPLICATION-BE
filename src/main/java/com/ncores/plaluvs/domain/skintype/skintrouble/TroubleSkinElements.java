package com.ncores.plaluvs.domain.skintype.skintrouble;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public enum TroubleSkinElements {
    //검은반점 개선
    알부틴("알부틴")
    ,코직애씨드("코직애씨드")
    ,검은뽕나무열매추출물("검은뽕나무열매추출물")
    ,뽕나무열매추출물("뽕나무열매추출물")
    ,꾸지뽕나무열매추출물("꾸지뽕나무열매추출물")
    ,코코넛야자열매즙("코코넛야자열매즙")
    ,나이아신아마이드("나이아신아마이드")
    ,오이추출물("오이추출물")
    ,레조시놀("레조시놀")
    ,분홍바늘꽃추출물("분홍바늘꽃추출물")
    ,레티놀("레티놀")
    ,갈릭애씨드("갈릭애씨드")
    ,살리실릭애씨드("살리실릭애씨드")
    ,감초뿌리추출물("감초뿌리추출물")
    ,바위취추출물("바위취추출물")
    ,아스코빅애씨드("아스코빅애씨드")

    // 잡티 개선
    ,오이열매추출물("오이열매추출물")
    ,창과감초뿌리추출물("창과감초뿌리추출물")
    ,스페인감초뿌리추출물("스페인감초뿌리추출물")

    //여드름 개선
    ,티트리잎오일("티트리잎오일");

    private String name;
}
