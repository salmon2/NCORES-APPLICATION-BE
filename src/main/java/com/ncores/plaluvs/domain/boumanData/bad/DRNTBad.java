package com.ncores.plaluvs.domain.boumanData.bad;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DRNTBad {
 아세톤("아세톤")
,에탄올("에탄올")
,변성알코올("변성알코올")
,메틸알코올("메틸알코올")
,벤질알코올("벤질알코올")
,아이소프로필알코올("아이소프로필알코올");



    private String name;
}
