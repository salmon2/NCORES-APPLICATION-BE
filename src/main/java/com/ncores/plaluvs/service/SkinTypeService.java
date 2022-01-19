package com.ncores.plaluvs.service;

import com.ncores.plaluvs.domain.Elements;
import com.ncores.plaluvs.domain.SkinTypeBadElements;
import com.ncores.plaluvs.domain.SkinTypeGoodElements;
import com.ncores.plaluvs.domain.boumanData.good.*;
import com.ncores.plaluvs.domain.skintype.Bouman;
import com.ncores.plaluvs.domain.skintype.SkinType;
import com.ncores.plaluvs.repository.ElementsRepository;
import com.ncores.plaluvs.repository.STBadElementRepository;
import com.ncores.plaluvs.repository.STGoodElementsRepository;
import com.ncores.plaluvs.repository.SkinTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SkinTypeService {
    private final SkinTypeRepository skinTypeRepository;
    private final STGoodElementsRepository stGoodElementsRepository;
    private final ElementsRepository elementsRepository;
    private final STBadElementRepository stBadElementRepository;


    @Transactional
    public void findSkinElements(SkinType skinType){
        Bouman bouman = skinType.getBouman();
        String type = bouman.getName();

        saveGoodElements(skinType, type);
        saveBadElements(skinType, type);
    }

    private void saveGoodElements(SkinType skinType, String type) {
        if(type.equals("DRNT")){
            saveSTGoodElements(skinType, DRNTGood.values(), "DRNT");
        }
        if(type.equals("DRNW")){
            saveSTGoodElements(skinType, DRNWGood.values(), "DRNW");
        }
        if(type.equals("DRPT")){
            saveSTGoodElements(skinType, DRPTGood.values(), "DRPT");
        }
        if(type.equals("DRPW")){
            saveSTGoodElements(skinType, DRPWGood.values(), "DRPW");
        }
        if(type.equals("DSNW")){
            saveSTGoodElements(skinType, DSNWGood.values(), "DSNW");
        }
        if(type.equals("DSPT")){
            saveSTGoodElements(skinType, DSPTGood.values(), "DSPT");
        }
        if(type.equals("DRPW")){
            saveSTGoodElements(skinType, DRPWGood.values(), "DRPW");
        }
        if(type.equals("ORNT")){
            saveSTGoodElements(skinType, ORNTGood.values(), "ORNT");
        }
        if(type.equals("ORNW")){
            saveSTGoodElements(skinType, ORNWGood.values(), "ORNW");
        }
        if(type.equals("ORPT")){
            saveSTGoodElements(skinType, ORPTGood.values(), "ORPT");
        }
        if(type.equals("OSPW")){
            saveSTGoodElements(skinType, OSPWGood.values(), "OSPW");
        }
        if(type.equals("OSNT")){
            saveSTGoodElements(skinType, OSNTGood.values(), "OSNT");
        }
        if(type.equals("OSNW")){
            saveSTGoodElements(skinType, OSNWGood.values(), "OSNW");
        }
        if(type.equals("OSPT")){
            saveSTGoodElements(skinType, OSPTGood.values(), "OSPT");
        }
        if(type.equals("OSPW")){
            saveSTGoodElements(skinType, OSPWGood.values(), "OSPW");
        }
    }


    private <T> void saveSTGoodElements(SkinType skinType, T[] array, String type) {
        for (T value : array) {
            Elements findElements = elementsRepository.findByKorean((String) value);
            if(findElements == null)
                continue;
            else{
                SkinTypeGoodElements newSkinTypeGood
                        = new SkinTypeGoodElements(skinType, findElements, type);
                stGoodElementsRepository.save(newSkinTypeGood);
            }
        }
    }

    private void saveBadElements(SkinType skinType, String type) {
        if(type.equals("DRNT")){
            saveSTBadElements(skinType, DRNTGood.values(), "DRNT");
        }
        if(type.equals("DRNW")){
            saveSTBadElements(skinType, DRNWGood.values(), "DRNW");
        }
        if(type.equals("DRPT")){
            saveSTBadElements(skinType, DRPTGood.values(), "DRPT");
        }
        if(type.equals("DRPW")){
            saveSTBadElements(skinType, DRPWGood.values(), "DRPW");
        }
        if(type.equals("DSNW")){
            saveSTBadElements(skinType, DSNWGood.values(), "DSNW");
        }
        if(type.equals("DSPT")){
            saveSTBadElements(skinType, DSPTGood.values(), "DSPT");
        }
        if(type.equals("DRPW")){
            saveSTBadElements(skinType, DRPWGood.values(), "DRPW");
        }
        if(type.equals("ORNT")){
            saveSTBadElements(skinType, ORNTGood.values(), "ORNT");
        }
        if(type.equals("ORNW")){
            saveSTBadElements(skinType, ORNWGood.values(), "ORNW");
        }
        if(type.equals("ORPT")){
            saveSTBadElements(skinType, ORPTGood.values(), "ORPT");
        }
        if(type.equals("OSPW")){
            saveSTBadElements(skinType, OSPWGood.values(), "OSPW");
        }
        if(type.equals("OSNT")){
            saveSTBadElements(skinType, OSNTGood.values(), "OSNT");
        }
        if(type.equals("OSNW")){
            saveSTBadElements(skinType, OSNWGood.values(), "OSNW");
        }
        if(type.equals("OSPT")){
            saveSTBadElements(skinType, OSPTGood.values(), "OSPT");
        }
        if(type.equals("OSPW")){
            saveSTBadElements(skinType, OSPWGood.values(), "OSPW");
        }
    }


    private <T> void saveSTBadElements(SkinType skinType, T[] array, String type) {
        for (T value : array) {
            Elements findElements = elementsRepository.findByKorean((String) value);
            if(findElements == null)
                continue;
            else{
                SkinTypeBadElements newSkinTypeBad
                        = new SkinTypeBadElements(skinType, findElements, type);
                stBadElementRepository.save(newSkinTypeBad);
            }
        }
    }
}
