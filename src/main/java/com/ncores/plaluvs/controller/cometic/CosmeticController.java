package com.ncores.plaluvs.controller.cometic;

import com.ncores.plaluvs.controller.cometic.dto.CosmeticElementsResponseDto;
import com.ncores.plaluvs.controller.cometic.dto.DetailCosmeticDto;
import com.ncores.plaluvs.controller.cometic.dto.SimpleCosmeticDto;
import com.ncores.plaluvs.crawling.CrawlingItemDto;
import com.ncores.plaluvs.crawling.ReadJsonFile;
import com.ncores.plaluvs.domain.Elements;
import com.ncores.plaluvs.domain.UserCosmetic;
import com.ncores.plaluvs.domain.dto.PagingResponseDto;
import com.ncores.plaluvs.domain.dto.PagingSimpleResponseDto;
import com.ncores.plaluvs.exception.ErrorCode;
import com.ncores.plaluvs.exception.PlaluvsException;
import com.ncores.plaluvs.repository.elements.ElementsRepository;
import com.ncores.plaluvs.security.UserDetailsImpl;
import com.ncores.plaluvs.service.CosmeticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CosmeticController {
    private final ReadJsonFile readJsonFile;
    private final CosmeticService cosmeticService;

    //수정할 것
    private final ElementsRepository elementsRepository;


    @GetMapping("/item/save")
    public void itemSave() throws PlaluvsException, IOException, ParseException {
        List<CrawlingItemDto> crawlingItemDtoList = readJsonFile.readJsonFile();

        log.info("crawlingItemDtoList = {}", crawlingItemDtoList.get(0).getName());
        log.info("size = {}", crawlingItemDtoList.size());

        readJsonFile.saveJsonFile();
    }


    @GetMapping("/cosmetic/simple-recommends")
    public ResponseEntity<?> cosmeticSimpleRecommends(@AuthenticationPrincipal UserDetailsImpl userDetails) throws PlaluvsException {
        UserDetailsImpl.UserCheck(userDetails);
        List<SimpleCosmeticDto> result = cosmeticService.cosmeticSimpleRecommends(userDetails);

        return new ResponseEntity<>(new PagingSimpleResponseDto(result.size(), result), HttpStatus.OK);
    }

    @GetMapping(value = {"/cosmetic/detail-recommends/{category}/{page}", "/cosmetic/detail-recommends"})
    public ResponseEntity<?> cosmeticDetailRecommends(
            @PathVariable(value = "category", required = false) Long categoryId,
            @PathVariable(required = false) Long page,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam(defaultValue = "asc") String sort
    ) throws PlaluvsException {
        UserDetailsImpl.UserCheck(userDetails);
        page = (page == null) ? 0L : page;
        categoryId = (categoryId == null) ? 618L : categoryId;

        Page<DetailCosmeticDto> result = cosmeticService.cosmeticDetailRecommends(userDetails, categoryId, page, sort);

        return new ResponseEntity<>(new PagingResponseDto(result.getContent().size(), result.getNumber(), result.getTotalPages(), result.getContent()), HttpStatus.OK);
    }

    @GetMapping(value = {"/cosmetic/elements-recommend/{elements}/{category}/{page}", "/cosmetic/elements-recommend/{elements}"})
    public ResponseEntity<?> cosmeticElements(@PathVariable(value = "elements") Long elementsId,
                                              @PathVariable(value = "category", required = false) Long categoryId,
                                              @PathVariable(required = false) Long page,
                                              @AuthenticationPrincipal UserDetailsImpl userDetails,
                                              @RequestParam(defaultValue = "asc") String sort
                                              ) throws PlaluvsException {
        page = (page == null) ? 0L : page;
        categoryId = (categoryId == null) ? 618L : categoryId;
        Page<DetailCosmeticDto> result = cosmeticService.cosmeticContainsElements(userDetails, elementsId, categoryId, page, sort);


        return new ResponseEntity<>(new PagingResponseDto(result.getContent().size(), result.getNumber(), result.getTotalPages(), result.getContent()), HttpStatus.OK);
    }

    @GetMapping("/cosmetic/worry-recommends")
    public ResponseEntity<?> cosmeticWorry(@AuthenticationPrincipal UserDetailsImpl userDetails) throws PlaluvsException {
        List<SimpleCosmeticDto> result = cosmeticService.cosmeticWorry(userDetails);

        return new ResponseEntity<>(new PagingSimpleResponseDto(result.size(), result), HttpStatus.OK);
    }

    @PostMapping("/cosmetic/mark/{cosmetic}")
    public ResponseEntity<?> cosmeticMark(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long cosmetic) throws PlaluvsException {

        String result = cosmeticService.cosmeticMark(userDetails, cosmetic);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
