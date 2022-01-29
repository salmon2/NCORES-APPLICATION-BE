package com.ncores.plaluvs.domain.skintype.skindailyStatus;


import com.ncores.plaluvs.exception.ErrorCode;
import com.ncores.plaluvs.exception.PlaluvsException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@AllArgsConstructor
@Slf4j
public enum SkinDailyStatusEnum {

    Sting("따가움", 1L),
    Dry("건조함", 2L),
    Trouble("트러블", 3L),
    Oily("유분기", 4L),
    None("없음", 5L);




    private String content;
    private Long id;


    public static SkinDailyStatusEnum findDailySkinEnum(Long id) throws PlaluvsException {
        for (SkinDailyStatusEnum value : SkinDailyStatusEnum.values()) {
            if(value.getId().equals(id)){
                log.info("skinEnum = {}", value);
                return value;
            }
        }
        throw new PlaluvsException(ErrorCode.DATA_NOT_TYPE);
    }
}
