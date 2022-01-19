package com.ncores.plaluvs.controller.skin;

import com.ncores.plaluvs.controller.skin.dto.*;
import com.ncores.plaluvs.domain.dto.OilStatusRequestDto;
import com.ncores.plaluvs.domain.dto.SkinWorryRequestDto;
import com.ncores.plaluvs.exception.PlaluvsException;
import com.ncores.plaluvs.security.UserDetailsImpl;
import com.ncores.plaluvs.service.SkinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SkinController {
    private final SkinService skinService;


    @PostMapping("/skin/oil-indicate")
    public ResponseEntity<?> skinOilIndicate(@RequestBody OilStatusRequestDto requestDto,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails) throws PlaluvsException {
        log.info("/skin/oil-indicate");
        log.info("requestDto = {}", requestDto);

        UserDetailsImpl.UserCheck(userDetails);

        skinService.currentSkinStatus(requestDto, userDetails);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/skin/worry")
    public ResponseEntity<?> skinWorryIndicate(@RequestBody SkinWorryRequestDto requestDto,
                                               @AuthenticationPrincipal UserDetailsImpl userDetails) throws PlaluvsException {

        log.info("/skin/worry");
        log.info("requestDto = {}", requestDto);

        UserDetailsImpl.UserCheck(userDetails);
        skinService.skinWorryUpdate(requestDto, userDetails);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/skin/daily/status")
    public ResponseEntity<?> skinDailyStatus(@RequestBody SkinDailyStatusRequestDto requestDto,
                                               @AuthenticationPrincipal UserDetailsImpl userDetails) throws PlaluvsException {

        log.info("/skin/daily/status");
        log.info("requestDto = {}", requestDto);

        UserDetailsImpl.UserCheck(userDetails);
        skinService.skinDailyStatus(requestDto, userDetails);

        return null;
    }

    @PostMapping("/skin/daily/Stimulation")
    public ResponseEntity<?> skinDailyStimulation(@RequestBody SkinDailyStimulationRequestDto requestDto,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails) throws PlaluvsException {

        log.info("/skin/daily/status");
        log.info("requestDto = {}", requestDto);

        UserDetailsImpl.UserCheck(userDetails);
        skinService.skinDailyStimulation(requestDto, userDetails);

        return null;
    }
    @PostMapping("skin/daily/self-check")
    public ResponseEntity<?> skinDailySefCheck(@RequestBody SkinDailySefCheckRequestDto requestDto,
                                               @AuthenticationPrincipal UserDetailsImpl userDetails
                                               ) throws PlaluvsException{
        log.info("skin/daily/self-check");
        log.info("requestDto = {}", requestDto);

        UserDetailsImpl.UserCheck(userDetails);
        skinService.skinSelfCheck(requestDto, userDetails);

        return new ResponseEntity<>(HttpStatus.OK);
    }



    @GetMapping("/skin/status")
    public ResponseEntity<?> skinStatus(@AuthenticationPrincipal UserDetailsImpl userDetails){
        log.info("/skin/status");
        SkinStatusResponseDto skinStatusResponseDto = skinService.skinStatus();

        return new ResponseEntity<>(skinStatusResponseDto, HttpStatus.OK);
    }

    @GetMapping("/skin/status/list")
    public ResponseEntity<?> skinStatusList(@AuthenticationPrincipal UserDetailsImpl userDetails){
        log.info("skin/status/list");
        SkinStatusListResponseDto result = skinService.skinStatusList();

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

}


