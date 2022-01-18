package com.ncores.plaluvs.domain.skintrouble;

import com.ncores.plaluvs.exception.ErrorCode;
import com.ncores.plaluvs.exception.PlaluvsException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public enum SkinTroubleEnum {
    TROUBLE_SKIN("모공 또는 트러블성 피부", 1L),
    WRINKLES_SKIN("탄력 또는 주름이 고민인 피부", 2L),
    SENSITIVE_SKIN("민감한 피부", 3L),
    PIGMENTATION_SKIN("색소침착 또는 칙칙한 피부", 4L),
    UNBALANCE_SKIN("유수분의 불균형인 피부", 5L);


    private String content;
    private Long id;


    public static SkinTroubleEnum findSkinTroubleEnum(Long id) throws PlaluvsException {
        for (SkinTroubleEnum value : SkinTroubleEnum.values()) {
            if(value.getId().equals(id)){
                log.info("skinEnum = {}", value);
                return value;
            }
        }
        throw new PlaluvsException(ErrorCode.DATA_EMPTY);
    }
}
