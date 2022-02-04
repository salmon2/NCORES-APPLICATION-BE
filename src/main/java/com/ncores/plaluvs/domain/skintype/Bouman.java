package com.ncores.plaluvs.domain.skintype;

import com.ncores.plaluvs.exception.ErrorCode;
import com.ncores.plaluvs.exception.PlaluvsException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Bouman {
    DRPT("DRPT" ),
    DRNT("DRNT" ),
    DSPT("DSPT" ),
    DSNT("DSNT" ),
    DRPW("DRPW" ),
    DRNW("DRNW" ),
    DSPW("DSPW" ),
    DSNW("DSNW" ),
    ORPT("ORPT" ),
    ORNT("ORNT" ),
    OSPT("OSPT" ),
    OSNT("OSNT" ),
    ORPW("ORPW" ),
    ORNW("ORNW" ),
    OSPW("OSPW" ),
    OSNW("OSNW" );

    private String name;

    public static Bouman findBoumanBySkinType(String skinType) throws PlaluvsException {

        for (Bouman value : Bouman.values()) {
            if(value.getName().equals(skinType))
                return value;
        }

        throw new PlaluvsException(ErrorCode.BOUMAN_NOT_FOUND);
    }

}
