package com.ncores.plaluvs.crawling;


import com.ncores.plaluvs.domain.Category;
import com.ncores.plaluvs.domain.Elements;
import com.ncores.plaluvs.domain.Item;
import com.ncores.plaluvs.exception.PlaluvsException;
import com.ncores.plaluvs.repository.CategoryRepository;
import com.ncores.plaluvs.repository.ElementsRepository;
import com.ncores.plaluvs.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import javax.swing.text.Element;
import javax.transaction.Transactional;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ReadJsonFile {
    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;
    private final ElementsRepository elementsRepository;


    public List<CrawlingItemDto> readJsonFile() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();

        FileReader reader = new FileReader("D:\\회사\\plaluvs\\src\\main\\resources\\static\\item_json.json");
        JSONObject jsonObject = (JSONObject)jsonParser.parse(reader);
        String str = jsonObject.get("item_list").toString();

        JSONArray jsonArray = new JSONArray(str);
        List<CrawlingItemDto> crawlingItemDtoList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            Map<String, Object> map = jsonArray.getJSONObject(i).toMap();
            CrawlingItemDto crawlingItemDto = new CrawlingItemDto(map);
            crawlingItemDtoList.add(crawlingItemDto);
        }

        return crawlingItemDtoList;
    }

    public void saveItemToRepository (List<CrawlingItemDto> crawlingItemDtoList) throws PlaluvsException {
        for (CrawlingItemDto crawlingItemDto : crawlingItemDtoList) {
            List<String> category = crawlingItemDto.getCategory();

            Category findCategory = CreateCategory(category);
            Item findItem = CreateItem(crawlingItemDto, findCategory);

            createdElements(crawlingItemDto.getElements(), findItem);

        }
    }

    @Transactional
    public void createdElements(List<Map<String, String>> crawlingItemDto, Item findItem) {
        for (Map<String, String> element : crawlingItemDto) {
            Elements newElements = new Elements(
                            element.get("level"),
                            element.get("korean"),
                            element.get("english"),
                            element.get("purpose"),
                            findItem
            );
            elementsRepository.save(newElements);
        }
    }

    @Transactional
    public Item CreateItem(CrawlingItemDto crawlingItemDto, Category findCategory) {
        Item item = new Item(crawlingItemDto, findCategory);
        Item save = itemRepository.save(item);
        return save;

    }

    @Transactional
    public Category CreateCategory(List<String> crawlingCategory) throws PlaluvsException {
        String mainCategory = crawlingCategory.get(0);
        Optional<Category> result = categoryRepository.findByCategoryLarge(mainCategory);

        if (!result.isPresent()) {
            Category resultCategory = new Category(crawlingCategory.get(0));
            categoryRepository.save(resultCategory);
            return resultCategory;
        }

        return result.get();
    }


    public static void main(String[] args) throws IOException, ParseException {

    }
}
