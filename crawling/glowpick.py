from selenium import webdriver
from selenium.webdriver import ActionChains
import time
import os
import re
import json


standard_category = [
"립스틱"
,"스킨/토너"
,"에센스/세럼"
,"로션/에멀젼"
,"크림"
,"미스트"
,"페이스오일"
,"파운데이션"
,"베이스/프라이머"
,"BB크림"
,"CC크림"
,"립베이스"
,"립케어"
,"아이섀도우"
,"아이라이너"
,"아이브로우"
,"마스카라"
,"아이프라이머"
,"블러셔"
,"쉐딩"
,"하이라이터"
,"컨투어링팔레트"
,"멀티팔레트"
,"페이셜클렌저"
,"메이크업클렌저"
,"포인트리무버"
,"각질케어"
,"선크림"
,"선쿠션"
,"선스틱"
,"선젤"
,"선베이스"
,"선스프레이"
,"향수"
,"남성 스킨케어"
,"남성 메이크업"
,"남성 클렌징"
,"남성 쉐이빙"]


path = './data/GP/'


def getProductInfo(driver):
    p_name = driver.find_element_by_class_name('product__summary__name').text
    tmp = driver.find_element_by_class_name("offer__volume-price").text.split('/')
    p_volume = tmp[0].strip()
    p_price = tmp[1].strip()
    p_brand = driver.find_element_by_class_name('product__summary__brand__name').text
    p_discription = driver.find_element_by_class_name('description__text').text
    p_tags = []
    tags = driver.find_elements_by_class_name('description__list')[0].text.split(' ')
    for t in tags:
        t = t.replace('#','')
        p_tags.append(t)
    
    img = driver.find_element_by_css_selector('[alt="제품 이미지"]').get_attribute('src')
    elements = []
    
    btn_elements = driver.find_element_by_class_name('info__article__h3__button')
    btn_elements.click()
    time.sleep(0.5)
    e_list = driver.find_elements_by_class_name('ingredient__list')[0].find_elements_by_tag_name("li")
    elements = []
    for e in e_list:
        try:
            level = e.find_element_by_class_name('tag__label').text
        except:
            level = '-'
        korean = e.find_element_by_class_name('item__wrapper__text__kor').text
        english = e.find_element_by_class_name('item__wrapper__text__eng').text
        purpose = e.find_element_by_class_name('item__wrapper__text__desc').text
        elements.append({'level': level, 'korean': korean, 'english': english, 'purpose': purpose})
    btn_close = driver.find_element_by_class_name('details__contents__h1__button')
    btn_close.click()

    colors = ''

    return {'name':p_name, 'image' : img, 'volume':p_volume, 'price':p_price, 'brand':p_brand, 'discription' :p_discription, 'tags':p_tags, 'elements':elements, 'colors':colors} 


def writeJsonFile(start_num, end_num, items):
    file_path = path + "/output/"
    if not os.path.exists(file_path):
        os.makedirs(file_path)
    item_outpath = file_path + "GP_items_"+str(start_num)+"-"+str(end_num)+".json"

    with open(item_outpath, 'w', encoding="utf-8") as of:
        json.dump(items, of, ensure_ascii=False, indent="\t")

def getProducts(start_num, end_num):
    options = webdriver.ChromeOptions() 
    options.add_experimental_option("excludeSwitches", ["enable-logging"])

    baseUrl = 'https://www.glowpick.com/product/'
    driver = webdriver.Chrome(options=options, executable_path='chrome/chromedriver')

    items = []
    for i in range(start_num, end_num+1): 
        url = baseUrl + str(i)
        driver.get(url=url)  # url open
        time.sleep(1)
        print(str(i)+" / "+ str(end_num))
        try :
            p_name = driver.find_element_by_class_name('product__summary__name').text
        except:
            continue
        if '단종' in p_name:
            continue

        switch = False
        categories = driver.find_element_by_class_name('rank-item__name').text
        
        for st_category in standard_category:
            if categories in st_category:
                switch = True
                categories = st_category
            if st_category in categories:
                switch = True
                categories = st_category
        
                
        cosmetic = getProductInfo(driver)
        cosmetic['id'] = i
        cosmetic['category'] = categories
        
        time.sleep(0.5)
        print(cosmetic)
     
        items.append(cosmetic)
        

    writeJsonFile(start_num, end_num, items)
    driver.quit()
    

if __name__ == '__main__':
    getProducts(4,15)

