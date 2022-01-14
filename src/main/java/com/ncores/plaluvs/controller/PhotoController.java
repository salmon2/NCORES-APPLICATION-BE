package com.ncores.plaluvs.controller;

import com.ncores.plaluvs.domain.Photo;
import com.ncores.plaluvs.exception.ErrorCode;
import com.ncores.plaluvs.exception.PlaluvsException;
import com.ncores.plaluvs.service.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PhotoController {
    private final PhotoService photoService;

    @PostMapping(value = "/photo")
    public ResponseEntity<?> uploadFile(@RequestParam("image") MultipartFile file)
            throws PlaluvsException, IOException {
        log.info("/upload");
        log.info("file = {}", file.getOriginalFilename());

        Photo.FileCheck(file);
        String url = photoService.upload(file, "static");
        log.info("url = {}", url);


        return new ResponseEntity<>(url, HttpStatus.OK);
    }



}
