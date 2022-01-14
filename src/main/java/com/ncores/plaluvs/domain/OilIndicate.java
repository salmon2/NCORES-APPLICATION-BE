package com.ncores.plaluvs.domain;


import com.ncores.plaluvs.exception.ErrorCode;
import com.ncores.plaluvs.exception.PlaluvsException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Getter
@Slf4j
public enum OilIndicate {
    DRY("D", 1L),
    DEHYDRATED("DH",2L),
    OILY("O", 3L),
    COMBINATION("C", 4L),
    NEUTRAL("N", 5L);


    private String Initial;
    private Long id;


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
