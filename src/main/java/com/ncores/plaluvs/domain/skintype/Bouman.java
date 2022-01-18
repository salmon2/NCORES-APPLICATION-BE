package com.ncores.plaluvs.domain.skintype;

import com.ncores.plaluvs.exception.ErrorCode;
import com.ncores.plaluvs.exception.PlaluvsException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Bouman {
    DRPT("DRPT"),
    DRNT("DRNT"),
    DSPT("DSPT"),
    DSNT("DSNT"),
    DRPW("DRPW"),
    DRNW("DRNW"),
    DSPW("DSPW"),
    DSNW("DSNW"),
    ORPT("ORPT"),
    ORNT("ORNT"),
    OSPT("OSPT"),
    OSNT("OSNT"),
    ORPW("ORPW"),
    ORNW("ORNW"),
    OSPW("OSPW"),
    OSNW("OSNW");

    private String name;

    public Bouman findBoumanBySkinType(SkinType skinType) throws PlaluvsException {
        String key = "";

        findBoumanOilIndicate(skinType, key);
        findBoumanSensitivity(skinType, key);
        findBoumanPigment(skinType, key);
        findBoumanWinkle(skinType, key);

        for (Bouman value : Bouman.values()) {
            if(value.getName().equals(key))
                return value;
        }

        throw new PlaluvsException(ErrorCode.BOUMAN_NOT_FOUND);
    }

    private void findBoumanWinkle(SkinType skinType, String key) {
        if(findWinkle(skinType, Winkle.DRY)
                || findWinkle(skinType, Winkle.DEHYDRATED)
                ||findWinkle(skinType, Winkle.NEUTRAL))
            key += "T";
        else if (findWinkle(skinType, Winkle.OILY) || findWinkle(skinType, Winkle.COMBINATION))
            key += "W";
        else
            key += "T";
    }

    private Boolean findWinkle(SkinType skinType, Winkle winkle){
        return skinType.getPigment().equals(winkle);
    }



    private void findBoumanPigment(SkinType skinType, String key) {
        if(findPigment(skinType, Pigment.DRY)
                || findPigment(skinType, Pigment.DEHYDRATED)
                ||findPigment(skinType, Pigment.NEUTRAL))
            key += "P";
        else if (findPigment(skinType, Pigment.OILY) || findPigment(skinType, Pigment.COMBINATION))
            key += "N";
        else
            key += "P";
    }

    private Boolean findPigment(SkinType skinType, Pigment pigment){
        return skinType.getPigment().equals(pigment);
    }


    private void findBoumanSensitivity(SkinType skinType, String key) {
        if(findSensitivity(skinType, Sensitivity.DRY)
                || findSensitivity(skinType, Sensitivity.DEHYDRATED)
                ||findSensitivity(skinType, Sensitivity.NEUTRAL))
            key += "R";
        else if (findSensitivity(skinType, Sensitivity.OILY) || findSensitivity(skinType, Sensitivity.COMBINATION))
            key += "S";
        else
            key += "R";
    }

    private Boolean findSensitivity(SkinType skinType, Sensitivity sensitivity){
        return skinType.getSensitivity().equals(sensitivity);

    }

    private void findBoumanOilIndicate(SkinType skinType, String key) {
        if(findOilIndicate(skinType, OilIndicate.DRY)
                || findOilIndicate(skinType, OilIndicate.DEHYDRATED)
                ||findOilIndicate(skinType, OilIndicate.NEUTRAL))
            key += "D";
        else if (findOilIndicate(skinType, OilIndicate.OILY) || findOilIndicate(skinType, OilIndicate.COMBINATION))
            key += "O";
        else
            key += "D";
    }


    private Boolean findOilIndicate(SkinType skinType, OilIndicate oilIndicate){
        return skinType.getOilIndicate().equals(oilIndicate);
    }

}
