package com.ncores.plaluvs.controller;

import com.ncores.plaluvs.exception.ErrorCode;
import com.ncores.plaluvs.exception.PlaluvsException;
import com.ncores.plaluvs.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class PhotoController {
    private final PhotoService photoService;

    @PostMapping(value = "/upload")
    public ResponseEntity<?> uploadFile(MultipartFile file) throws PlaluvsException {
        if(file.isEmpty()) {
            throw new PlaluvsException(ErrorCode.DATA_EMPTY);
        }
        photoService.uploadFile(file);



        return new ResponseEntity<>(HttpStatus.OK);
    }

}
