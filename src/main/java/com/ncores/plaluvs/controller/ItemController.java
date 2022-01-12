package com.ncores.plaluvs.controller;

import com.ncores.plaluvs.crawling.CrawlingItemDto;
import com.ncores.plaluvs.crawling.ReadJsonFile;
import com.ncores.plaluvs.exception.PlaluvsException;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ReadJsonFile readJsonFile;

    @GetMapping("/item/save")
    public void itemSave() throws PlaluvsException, IOException, ParseException {
        List<CrawlingItemDto> crawlingItemDtoList = readJsonFile.readJsonFile();
        readJsonFile.saveItemToRepository(crawlingItemDtoList);
    }

}
