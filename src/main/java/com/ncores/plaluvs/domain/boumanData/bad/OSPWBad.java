package com.ncores.plaluvs.domain.boumanData.bad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum OSPWBad {
    //여드름 증가 (여드름이 있는 경우)
    부틸스테아레이트("부틸스테아레이트")
    ,육계잎오일("육계잎오일")
    ,육계전초오일("육계전초오일")
    ,코코넛야자오일("코코넛야자오일")
    ,데실올리에이트("데실올리에이트")
    ,아이소세틸스테아레이트("아이소세틸스테아레이트")
    ,아이소프로필아이소스테아레이트("아이소프로필아이소스테아레이트")
    ,아이소프로필미리스테이트("아이소프로필미리스테이트")
    ,아이소프로필팔미테이트("아이소프로필팔미테이트")
    ,아이소스테아릴네오펜타노에이트("아이소스테아릴네오펜타노에이트")
    ,라놀린("라놀린")
    ,미리스틸미리스테이트("미리스틸미리스테이트")
    ,미리스틸프로피오네이트("미리스틸프로피오네이트")
    ,페퍼민트오일("페퍼민트오일");


    private String name;
}
