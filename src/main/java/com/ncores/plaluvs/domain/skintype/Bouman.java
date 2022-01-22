package com.ncores.plaluvs.domain.skintype;

import com.ncores.plaluvs.exception.ErrorCode;
import com.ncores.plaluvs.exception.PlaluvsException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Bouman {
    DRPT("DRPT", 70L),
    DRNT("DRNT", 85L),
    DSPT("DSPT", 55L),
    DSNT("DSNT", 70L),
    DRPW("DRPW", 55L),
    DRNW("DRNW", 70L),
    DSPW("DSPW", 40L),
    DSNW("DSNW", 55L),
    ORPT("ORPT", 85L),
    ORNT("ORNT", 100L),
    OSPT("OSPT", 70L),
    OSNT("OSNT", 85L),
    ORPW("ORPW", 70L),
    ORNW("ORNW", 85L),
    OSPW("OSPW", 55L),
    OSNW("OSNW", 70L);

    private String name;
    private Long score;

    public static Bouman findBoumanBySkinType(String skinType) throws PlaluvsException {

        for (Bouman value : Bouman.values()) {
            if(value.getName().equals(skinType))
                return value;
        }

        throw new PlaluvsException(ErrorCode.BOUMAN_NOT_FOUND);
    }

}
