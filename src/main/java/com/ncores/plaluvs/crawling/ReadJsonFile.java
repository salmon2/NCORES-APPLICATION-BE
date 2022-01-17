package com.ncores.plaluvs.crawling;


import com.ncores.plaluvs.domain.Category;
import com.ncores.plaluvs.domain.Elements;
import com.ncores.plaluvs.domain.Cosmetic;
import com.ncores.plaluvs.domain.CosmeticElements;
import com.ncores.plaluvs.repository.CategoryRepository;
import com.ncores.plaluvs.repository.ElementsRepository;
import com.ncores.plaluvs.repository.ItemElementsRepository;
import com.ncores.plaluvs.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReadJsonFile {
    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;
    private final ElementsRepository elementsRepository;
    private final ItemElementsRepository itemElementsRepository;
    private static Long index = 0L;

    private List<CrawlingItemDto>  crawlingItemDtoList = new ArrayList<>();

    private List<Category> resultCategoryList = new ArrayList<>();
    private List<Cosmetic> resultCosmeticList = new ArrayList<>();
    private List<Elements> resultElementsList = new ArrayList<>();
    private List<CosmeticElements> resultCosmeticElementsList = new ArrayList<>();


    public List<CrawlingItemDto> readJsonFile() throws IOException, ParseException {
        log.info("crawling data start");
        JSONParser jsonParser = new JSONParser();

        //FileReader reader = new FileReader("C:\\Users\\박기남\\Desktop\\회사\\data\\item_small_json.json");
        FileReader reader = new FileReader("D:\\회사\\item_json.json");

        JSONObject jsonObject = (JSONObject)jsonParser.parse(reader);
        String str = jsonObject.get("item_list").toString();

        JSONArray jsonArray = new JSONArray(str);


        for (int i = 0; i < jsonArray.length(); i++) {
            Map<String, Object> map = jsonArray.getJSONObject(i).toMap();
            CrawlingItemDto crawlingItemDto = new CrawlingItemDto(map);
            crawlingItemDtoList.add(crawlingItemDto);
        }

        return crawlingItemDtoList;
    }

    public void saveJsonFile(){
        log.info("save data start");
        int i = 1;
        for (CrawlingItemDto crawlingItemDto : crawlingItemDtoList) {
            Category category = createCategory(crawlingItemDto.getCategory());
            Cosmetic saveCosmetic = createItem(crawlingItemDto, category);

            List<Map<String, String>> elements = crawlingItemDto.getElements();

            for (Map<String, String> value : elements) {
                Elements saveElements = createElements(value);
                createItemElements(saveCosmetic, saveElements);
            }

            if(i% 100 == 0){
                itemRepository.saveAll(resultCosmeticList);
                itemElementsRepository.saveAll(resultCosmeticElementsList);
                break;
            }
            i++;
        }
        log.info("end save data ");
    }

    public void createItemElements(Cosmetic saveCosmetic, Elements saveElements) {
        CosmeticElements cosmeticElements = new CosmeticElements(saveCosmetic, saveElements);
        resultCosmeticElementsList.add(cosmeticElements);
    }

    public Elements createElements(Map<String, String> value) {
        Elements findElements = elementsRepository.findByKorean(value.get("korean"));
            if(findElements == null){
                findElements = new Elements(value);
                elementsRepository.save(findElements);
            }

        return findElements;
    }

    public Cosmetic createItem(CrawlingItemDto crawlingItemDto, Category category) {
        Cosmetic cosmetic = new Cosmetic(crawlingItemDto, category);
        resultCosmeticList.add(cosmetic);

        return cosmetic;
    }

    public Category createCategory(List<String> category) {
        Category findCategory = categoryRepository.findByCategoryLarge(category.get(0));
        if(findCategory == null) {
            findCategory = Category.createCategoryByCrawling(category);
            categoryRepository.save(findCategory);
        }
        return findCategory;
    }

}
