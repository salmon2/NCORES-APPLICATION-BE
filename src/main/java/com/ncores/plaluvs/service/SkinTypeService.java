package com.ncores.plaluvs.service;

import com.ncores.plaluvs.domain.Elements;
import com.ncores.plaluvs.domain.SkinTypeBadElements;
import com.ncores.plaluvs.domain.SkinTypeGoodElements;
import com.ncores.plaluvs.domain.boumanData.good.*;
import com.ncores.plaluvs.domain.skintype.Bouman;
import com.ncores.plaluvs.domain.skintype.SkinType;
import com.ncores.plaluvs.domain.user.User;
import com.ncores.plaluvs.repository.elements.ElementsRepository;
import com.ncores.plaluvs.repository.STBadElementRepository;
import com.ncores.plaluvs.repository.STGoodElementsRepository;
import com.ncores.plaluvs.repository.skinType.SkinTypeRepository;
import com.ncores.plaluvs.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SkinTypeService {
    private final SkinTypeRepository skinTypeRepository;
    private final STGoodElementsRepository stGoodElementsRepository;
    private final ElementsRepository elementsRepository;
    private final STBadElementRepository stBadElementRepository;


    @Transactional
    public void findSkinElements(User user, LocalDateTime createdAt){
        SkinType skinType = findDailySkinType(user, createdAt);

        Bouman bouman = skinType.getBouman();
        String type = bouman.getName();

        saveGoodElements(skinType, type);
        saveBadElements(skinType, type);
    }

    private void saveGoodElements(SkinType skinType, String type) {
        if(type.equals("DRNT")){
            saveSTGoodElements(skinType, DRNTGood.values(), "DRNT");
        }
        else if(type.equals("DRNW")){
            saveSTGoodElements(skinType, DRNWGood.values(), "DRNW");
        }
        else if(type.equals("DRPT")){
            saveSTGoodElements(skinType, DRPTGood.values(), "DRPT");
        }
        else if(type.equals("DRPW")){
            saveSTGoodElements(skinType, DRPWGood.values(), "DRPW");
        }
        else if(type.equals("DSNW")){
            saveSTGoodElements(skinType, DSNWGood.values(), "DSNW");
        }
        else if(type.equals("DSPT")){
            saveSTGoodElements(skinType, DSPTGood.values(), "DSPT");
        }
        else if(type.equals("DRPW")){
            saveSTGoodElements(skinType, DRPWGood.values(), "DRPW");
        }
        else if(type.equals("DSNT")){
            saveSTGoodElements(skinType, DSNTGood.values(), "DSNT");
        }
        else if(type.equals("ORNT")){
            saveSTGoodElements(skinType, ORNTGood.values(), "ORNT");
        }
        else if(type.equals("ORNW")){
            saveSTGoodElements(skinType, ORNWGood.values(), "ORNW");
        }
        else if(type.equals("ORPT")){
            saveSTGoodElements(skinType, ORPTGood.values(), "ORPT");
        }
        else if(type.equals("OSPW")){
            saveSTGoodElements(skinType, OSPWGood.values(), "OSPW");
        }
        else if(type.equals("OSNT")){
            saveSTGoodElements(skinType, OSNTGood.values(), "OSNT");
        }
        else if(type.equals("OSNW")){
            saveSTGoodElements(skinType, OSNWGood.values(), "OSNW");
        }
        else if(type.equals("OSPT")){
            saveSTGoodElements(skinType, OSPTGood.values(), "OSPT");
        }
        else if(type.equals("OSPW")){
            saveSTGoodElements(skinType, OSPWGood.values(), "OSPW");
        }
    }


    private <T> void saveSTGoodElements(SkinType skinType, T[] array, String type) {
        if(skinType.getBouman().equals(Bouman.ORNT)){
            List<Elements> elements = elementsRepository.findTop10ByOrderByIdAsc();
            for (Elements element : elements) {
                SkinTypeGoodElements newSkinTypeGood
                        = new SkinTypeGoodElements(skinType, element, type);
                stGoodElementsRepository.save(newSkinTypeGood);
            }
        }

        for (T value : array) {
            String name = value.toString();
            Elements findElements = elementsRepository.findByKorean(name);
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
            Elements findElements = elementsRepository.findByKorean(value.toString());
            if(findElements == null)
                continue;
            else{
                SkinTypeBadElements newSkinTypeBad
                        = new SkinTypeBadElements(skinType, findElements, type);
                stBadElementRepository.save(newSkinTypeBad);
            }
        }
    }

    private SkinType findDailySkinType(User user, LocalDateTime createdAt) {
        LocalDateTime startDatetime = null;
        LocalDateTime endDatetime = null;
        if(createdAt == null) {
            startDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0)); //오늘 00:00:00
            endDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59)); //오늘 23:59:59
        }
        else{
            startDatetime = LocalDateTime.of(createdAt.toLocalDate(), LocalTime.of(0,0,0));
            endDatetime = LocalDateTime.of(createdAt.toLocalDate(), LocalTime.of(23,59,59));
        }


        SkinType findSkinType = skinTypeRepository.findTopByUserAndCreatedAtBetween(user, startDatetime, endDatetime);
        return findSkinType;
    }

    private void setLocalDateTime( LocalDateTime startDatetime, LocalDateTime endDatetime, LocalDateTime createdAt){
        if(createdAt == null) {
            startDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0)); //오늘 00:00:00
            endDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59)); //오늘 23:59:59
        }
        else{
            startDatetime = LocalDateTime.of(createdAt.toLocalDate(), LocalTime.of(0,0,0));
            endDatetime = LocalDateTime.of(createdAt.toLocalDate(), LocalTime.of(23,59,59));
        }
    }
}
