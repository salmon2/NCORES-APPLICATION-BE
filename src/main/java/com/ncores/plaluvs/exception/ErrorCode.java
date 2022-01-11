package com.ncores.plaluvs.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USERNAME_EMPTY(HttpStatus.NOT_FOUND, "Email을 입력해주세요."),
    USERNAME_DUPLICATE(HttpStatus.CONFLICT, "중복된 Email 입니다."),

    NICKNAME_EMPTY(HttpStatus.NOT_FOUND, "Nickname을 입력해주세요"),
    NICKNAME_DUPLICATE(HttpStatus.CONFLICT, "중복된 NIckName 입니다."),

    PASSWORD_EMPTY(HttpStatus.NOT_FOUND, "비밀번호를 입력해주세요."),
    PASSWORD_CHECK_EMPTY(HttpStatus.NOT_FOUND, "비밀번호 확인을 입력해주세요"),

    PASSWORD_NOT_EQUAL(HttpStatus.NOT_FOUND, "비밀번호와 비밀번화 확인과 일치하지 않습니다."),
    PASSWORD_FAIL(HttpStatus.FORBIDDEN, "적합하지 않는 비밀번호입니다."),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다.");



    private final HttpStatus httpStatus;
    private final String message;
}
