package com.ncores.plaluvs.controller.user;


import com.ncores.plaluvs.controller.user.dto.*;
import com.ncores.plaluvs.domain.dto.ElementsDto;
import com.ncores.plaluvs.domain.dto.PagingResponseDto;
import com.ncores.plaluvs.domain.dto.PagingSimpleResponseDto;
import com.ncores.plaluvs.domain.user.User;
import com.ncores.plaluvs.exception.PlaluvsException;
import com.ncores.plaluvs.security.UserDetailsImpl;
import com.ncores.plaluvs.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //회원탈퇴
    @DeleteMapping("/user")
    public ResponseEntity<?> deleteUser(@AuthenticationPrincipal UserDetailsImpl userDetails) throws PlaluvsException {
        UserDetailsImpl.UserCheck(userDetails);
        userService.deleteUser(userDetails);

        return new ResponseEntity<>("정상적으로 삭제되었습니다.", HttpStatus.OK);
    }

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

    @GetMapping("/user/elements/{page}")
    public ResponseEntity<?> userElements(@PathVariable Long page, @AuthenticationPrincipal UserDetailsImpl userDetails) throws PlaluvsException {

        Page<ElementsDto> result = userService.getUserElements(userDetails, page);

        return new ResponseEntity<>(new PagingResponseDto<>(result.getContent().size(), result.getNumber(), result.getTotalPages(), result.getContent()), HttpStatus.OK);
    }


    @GetMapping("/user/cosmetics/{page}")
    public ResponseEntity<?> userCosmetic(@PathVariable Long page, @AuthenticationPrincipal UserDetailsImpl userDetails) throws PlaluvsException {

        Page<CosmeticDto> result = userService.getUserCosmetics(userDetails, page);

        return new ResponseEntity<>(new PagingResponseDto(result.getContent().size(), result.getNumber(), result.getTotalPages(), result.getContent()), HttpStatus.OK);
    }


}

