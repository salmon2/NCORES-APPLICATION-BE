package com.ncores.plaluvs.controller;

import com.ncores.plaluvs.exception.PlaluvsException;
import com.ncores.plaluvs.security.UserDetailsImpl;
import com.ncores.plaluvs.service.ElementsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ElementsController {
    private final ElementsService elementsService;


    @PostMapping("/elements/mark/{elements}")
    public ResponseEntity<?> cosmeticMark(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                          @PathVariable Long elements) throws PlaluvsException {
        String result = elementsService.elementsMark(userDetails, elements);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }




}
