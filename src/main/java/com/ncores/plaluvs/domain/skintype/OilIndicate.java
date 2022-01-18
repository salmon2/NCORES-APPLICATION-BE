package com.ncores.plaluvs.domain.skintype;


import com.ncores.plaluvs.exception.ErrorCode;
import com.ncores.plaluvs.exception.PlaluvsException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Getter
@Slf4j
public enum OilIndicate {
    DRY("D", 1L, "건성"),
    DEHYDRATED("DH",2L, "수분 부족형 지성"),
    OILY("O", 3L, "지성"),
    COMBINATION("C", 4L, "복합성"),
    NEUTRAL("N", 5L, "중성");


    private String Initial;
    private Long id;
    private String content;


    public static OilIndicate findOilIndicate(Long id) throws PlaluvsException {
        for (OilIndicate value : OilIndicate.values()) {
            if(value.getId().equals(id)){
                log.info("skinEnum = {}", value);
                return value;
            }
        }

        throw new PlaluvsException(ErrorCode.DATA_EMPTY);
    }

}
