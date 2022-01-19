package com.ncores.plaluvs.domain.skintype.skindailystimulation;

import com.ncores.plaluvs.domain.skintype.skindailyStatus.SkinDailyStatusEnum;
import com.ncores.plaluvs.exception.ErrorCode;
import com.ncores.plaluvs.exception.PlaluvsException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public enum SkinDailyStimulationEnum {
    수면부족("수면 부족", 1L),
    스트레스("스트레스", 2L),
    생리주기("생리 주기", 3L),
    강추위("강취위 또는 뜨거운 햇빛 노출", 4L),
    없음("없음", 5L);



    private String content;
    private Long id;


    public static SkinDailyStimulationEnum findSkinDailyStimulationEnum(Long id) throws PlaluvsException {
        for (SkinDailyStimulationEnum value : SkinDailyStimulationEnum.values()) {
            if(value.getId().equals(id)){
                log.info("skinEnum = {}", value);
                return value;
            }
        }
        throw new PlaluvsException(ErrorCode.DATA_EMPTY);
    }
}
