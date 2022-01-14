package com.ncores.plaluvs.controller;

import com.ncores.plaluvs.crawling.CrawlingItemDto;
import com.ncores.plaluvs.crawling.ReadJsonFile;
import com.ncores.plaluvs.exception.PlaluvsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ItemController {
    private final ReadJsonFile readJsonFile;


    @GetMapping("/item/save")
    public void itemSave() throws PlaluvsException, IOException, ParseException {
        List<CrawlingItemDto> crawlingItemDtoList = readJsonFile.readJsonFile();

        log.info("crawlingItemDtoList = {}", crawlingItemDtoList.get(0).getName());
        log.info("size = {}", crawlingItemDtoList.size());
        readJsonFile.saveJsonFile();
    }

}
