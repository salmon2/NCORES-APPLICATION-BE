package com.ncores.plaluvs.controller;


import com.ncores.plaluvs.domain.dto.user.*;
import com.ncores.plaluvs.domain.user.User;
import com.ncores.plaluvs.exception.ErrorCode;
import com.ncores.plaluvs.exception.PlaluvsException;
import com.ncores.plaluvs.security.UserDetailsImpl;
import com.ncores.plaluvs.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //가입요청
    @PostMapping("/user")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequestDto requestDto) throws PlaluvsException {
        log.info("/signup");
        User user = userService.registerUser(requestDto);

        return new ResponseEntity<>(user.getId(), HttpStatus.OK);
    }

    //이메일 체크
    @PostMapping("/user/email/check")
    public ResponseEntity<?> emailCheck(@RequestBody EmailCheckRequestDto requestDto) throws PlaluvsException {
        log.info("/user/email/check");
        userService.emailCheckService(requestDto);

        return new ResponseEntity<>("등록 가능한 email 입니다.", HttpStatus.OK);
    }

    //닉네임 체크
    @PostMapping("/user/nickname/check")
    public ResponseEntity<?> nicknameCheck(@RequestBody NicknameCheckRequestDto requestDto)
            throws PlaluvsException {
        log.info("/user/nickname/check");
        userService.nickNameCheckService(requestDto);

        return new ResponseEntity<>("등록 가능한 nickname 입니다.", HttpStatus.OK);
    }

    //비밀번호 체크
    @PostMapping("/user/password/check")
    public ResponseEntity<?> passwordCheck(@RequestBody PasswordCheckRequestDto requestDto)
            throws PlaluvsException {
        log.info("/user/password/check");
        userService.passwordCheckService(requestDto);

        return new ResponseEntity<>("등록 가능한 password 입니다.", HttpStatus.OK);
    }

    //로그인
    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody SignInRequestDto requestDto) throws PlaluvsException {
        log.info("/user/login");
        SignInResponseDto signInResponseDto = userService.signIn(requestDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", signInResponseDto.getToken());

        return new ResponseEntity<>(signInResponseDto, httpHeaders, HttpStatus.OK);
    }

    //Age 설정
    @PatchMapping("/user/age")
    public ResponseEntity<?> setAge(@RequestBody PatchAgeRequestDto requestDto,
                                    @AuthenticationPrincipal UserDetailsImpl userDetails) throws PlaluvsException {

        UserDetailsImpl.UserCheck(userDetails);

        userService.setAge(requestDto, userDetails);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Gender 설정
    @PatchMapping("/user/gender")
    public ResponseEntity<?> setGender(@RequestBody PatchGenderRequestDto requestDto,
                                    @AuthenticationPrincipal UserDetailsImpl userDetails) throws PlaluvsException {
        UserDetailsImpl.UserCheck(userDetails);

        userService.setGender(requestDto, userDetails);

        return new ResponseEntity<>(HttpStatus.OK);
    }





}

