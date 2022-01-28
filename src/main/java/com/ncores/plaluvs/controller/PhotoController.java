package com.ncores.plaluvs.controller;

import com.ncores.plaluvs.domain.Photo;
import com.ncores.plaluvs.exception.ErrorCode;
import com.ncores.plaluvs.exception.PlaluvsException;
import com.ncores.plaluvs.security.UserDetailsImpl;
import com.ncores.plaluvs.service.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PhotoController {
    private final PhotoService photoService;

    @PostMapping(value = "/photo")
    public ResponseEntity<?> uploadFile(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam("image") MultipartFile file) throws PlaluvsException, IOException {

        log.info("/photo");
        log.info("file = {}", file.getOriginalFilename());
        UserDetailsImpl.UserCheck(userDetails);
        Photo.FileCheck(file);

        String url = photoService.upload(file, "static", userDetails);
        log.info("url = {}", url);


        return new ResponseEntity<>(url, HttpStatus.OK);
    }

    @PostMapping("/restTest")
    public String restTest(@RequestParam String str){
        return str + " : Rest Test 완료!!!";
    }

    @GetMapping(value = "/photo/cal")
    public ResponseEntity<?> cal(@AuthenticationPrincipal UserDetailsImpl userDetails){
        // RestTemplate 에 MessageConverter 세팅
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new FormHttpMessageConverter());
        converters.add(new StringHttpMessageConverter());

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(converters);

        // parameter 세팅
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("str", "thisistest");

        // REST API 호출
        String result = restTemplate.postForObject("http://localhost:8080/restTest/", map, String.class);
        System.out.println("------------------ TEST 결과 ------------------");
        System.out.println(result);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
