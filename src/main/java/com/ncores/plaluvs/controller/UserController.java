package com.ncores.plaluvs.controller;


import com.ncores.plaluvs.controller.dto.SignInResponseDto;
import com.ncores.plaluvs.domain.User;
import com.ncores.plaluvs.domain.dto.*;
import com.ncores.plaluvs.exception.PlaluvsException;
import com.ncores.plaluvs.security.jwt.JwtTokenProvider;
import com.ncores.plaluvs.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    //가입요청
    @PostMapping("/user")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequestDto requestDto) throws PlaluvsException {
        User user = userService.registerUser(requestDto);

        return new ResponseEntity<>(user.getId(), HttpStatus.OK);
    }

    //이메일 체크
    @PostMapping("/user/email/check")
    public ResponseEntity<?> emailCheck(@RequestBody EmailCheckRequestDto requestDto) throws PlaluvsException {
        userService.emailCheckService(requestDto);

        return new ResponseEntity<>("등록 가능한 email 입니다.", HttpStatus.OK);
    }

    //닉네임 체크
    @PostMapping("/user/nickname/check")
    public ResponseEntity<?> nicknameCheck(@RequestBody NicknameCheckRequestDto requestDto)
            throws PlaluvsException {
        userService.nickNameCheckService(requestDto);

        return new ResponseEntity<>("등록 가능한 nickname 입니다.", HttpStatus.OK);
    }

    //비밀번호 체크
    @PostMapping("/user/password/check")
    public ResponseEntity<?> passwordCheck(@RequestBody PasswordCheckRequestDto requestDto)
            throws PlaluvsException {
        userService.passwordCheckService(requestDto);

        return new ResponseEntity<>("등록 가능한 password 입니다.", HttpStatus.OK);
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody SignInRequestDto requestDto) throws PlaluvsException {
        SignInResponseDto signInResponseDto = userService.signIn(requestDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", signInResponseDto.getToken());

        return new ResponseEntity<>(signInResponseDto, httpHeaders, HttpStatus.OK);
    }

}

