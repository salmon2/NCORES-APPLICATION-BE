package com.ncores.plaluvs.domain.boumanData.bad;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ORNWBad {
    //지성 피부 유발
    보리지씨오일("보리지씨오일")
    ,미네랄오일("미네랄오일")
    ,해바라기씨오일("해바라기씨오일")
    ,페트롤라툼("페트롤라툼");

    private String name;

}
