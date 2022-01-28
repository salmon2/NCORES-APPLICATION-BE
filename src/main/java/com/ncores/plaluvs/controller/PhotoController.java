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

        Photo savePhoto = photoService.upload(file, "static", userDetails);
        log.info("url = {}", savePhoto.getStored_file_path());

        Photo result = photoService.makeGetImageData(savePhoto);
        photoService.setSkinStatus(userDetails.getUser(), result);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/restTest")
    public String restTest(@RequestParam String str){
        return str + " : Rest Test 완료!!!";
    }



    @GetMapping(value = "/photo/cal")
    public ResponseEntity<?> cal(@AuthenticationPrincipal UserDetailsImpl userDetails){
        //return new ResponseEntity<>(result, HttpStatus.OK);
        return null;
    }



}
