package com.ncores.plaluvs.crawling;


import com.ncores.plaluvs.domain.Category;
import com.ncores.plaluvs.domain.Elements;
import com.ncores.plaluvs.domain.Item;
import com.ncores.plaluvs.domain.ItemElements;
import com.ncores.plaluvs.repository.CategoryRepository;
import com.ncores.plaluvs.repository.ElementsRepository;
import com.ncores.plaluvs.repository.ItemElementsRepository;
import com.ncores.plaluvs.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ReadJsonFile {
    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;
    private final ElementsRepository elementsRepository;
    private final ItemElementsRepository itemElementsRepository;
    private List<CrawlingItemDto>  crawlingItemDtoList = new ArrayList<>();


    public List<CrawlingItemDto> readJsonFile() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();

        FileReader reader = new FileReader("C:\\Users\\박기남\\Desktop\\회사\\data\\item_small_json.json");
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

    @Transactional
    public void saveJsonFile(){
        for (CrawlingItemDto crawlingItemDto : crawlingItemDtoList) {
            Category saveCategory = createCategory(crawlingItemDto.getCategory());
            Item saveItem = createItem(crawlingItemDto, saveCategory);

            List<Map<String, String>> elements = crawlingItemDto.getElements();

            for (Map<String, String> value : elements) {
                Elements saveElements = createElements(value);
                createItemElements(saveItem, saveElements);
            }

        }
    }

    @Transactional
    public void createItemElements(Item saveItem, Elements saveElements) {
        ItemElements itemElements = new ItemElements(saveItem, saveElements);
        itemElementsRepository.save(itemElements);
    }

    @Transactional
    public Elements createElements(Map<String, String> value) {
        Elements findElements = elementsRepository.findByKorean(value.get("korean"));
        if(findElements == null){
            findElements = new Elements(value);
            elementsRepository.save(findElements);
        }
        return findElements;
    }

    @Transactional
    public Item createItem(CrawlingItemDto crawlingItemDto, Category category) {
        Item item = new Item(crawlingItemDto, category);
        Item saveItem = itemRepository.save(item);

        return saveItem;
    }

    @Transactional
    public Category createCategory(List<String> category) {

        Category findCategory = categoryRepository.findByCategoryLarge(category.get(0));
        if(findCategory == null) {
            findCategory = new Category(category);
            categoryRepository.save(findCategory);
        }
        return findCategory;
    }


}
