package com.ncores.plaluvs.service;

import com.ncores.plaluvs.controller.dto.SignInResponseDto;
import com.ncores.plaluvs.domain.User;
import com.ncores.plaluvs.domain.UserRoleEnum;
import com.ncores.plaluvs.domain.dto.*;
import com.ncores.plaluvs.exception.ErrorCode;
import com.ncores.plaluvs.exception.PlaluvsException;
import com.ncores.plaluvs.repository.UserRepository;
import com.ncores.plaluvs.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;


    public User registerUser(SignUpRequestDto requestDto) throws PlaluvsException {
        emailCheck(requestDto.getEmail());
        nicknameCheck(requestDto.getNickname());
        passwordCheck(requestDto.getPassword(), requestDto.getConfirmPassword());

        User newUser = new User(
                requestDto.getEmail(),
                passwordEncoder.encode(requestDto.getPassword()),
                requestDto.getNickname(),
                UserRoleEnum.USER
        );

        userRepository.save(newUser);
        return newUser;
    }


    public void emailCheckService(EmailCheckRequestDto requestDto) throws PlaluvsException {
        emailCheck(requestDto.getEmail());
    }

    public void nickNameCheckService(NicknameCheckRequestDto requestDto) throws PlaluvsException {
        nicknameCheck(requestDto.getNickname());
    }

    public void passwordCheckService(PasswordCheckRequestDto requestDto) throws PlaluvsException {
        passwordCheck(requestDto.getPassword(), requestDto.getConfirmPassword());
    }


    private void emailCheck(String email) throws PlaluvsException {

        if (email.isEmpty()){
            throw new PlaluvsException(ErrorCode.USERNAME_EMPTY);
        }
        Optional<User> found = userRepository.findByUsername(email);
        if (found.isPresent()) {
            throw new PlaluvsException(ErrorCode.USERNAME_DUPLICATE);
        }
    }

    private void nicknameCheck(String nickname) throws PlaluvsException{
        if (nickname.isEmpty()){
            throw new PlaluvsException(ErrorCode.NICKNAME_EMPTY);
        }
        Optional<User> found2 = userRepository.findByNickname(nickname);
        if (found2.isPresent()) {
            throw new PlaluvsException(ErrorCode.NICKNAME_DUPLICATE);
        }
    }

    private void passwordCheck(String pw, String pwCheck) throws PlaluvsException {

        if (pw.isEmpty()){
            throw new PlaluvsException(ErrorCode.PASSWORD_EMPTY);
        }

        if (pwCheck.isEmpty()){
            throw new PlaluvsException(ErrorCode.PASSWORD_CHECK_EMPTY);
        }

        if(!pw.equals(pwCheck)){
            throw new PlaluvsException(ErrorCode.PASSWORD_NOT_EQUAL);
        }

        if(pw.length() <= 8 ){
            throw new PlaluvsException(ErrorCode.PASSWORD_FAIL);
        }
    }


    public SignInResponseDto signIn(SignInRequestDto requestDto) throws PlaluvsException {
        //데이터 적합성 체크
        String username = requestDto.getEmail();
        if (username.isEmpty()){
            throw new PlaluvsException(ErrorCode.USERNAME_EMPTY);
        }
        String password = requestDto.getPassword();
        if (password.isEmpty()){
            throw new PlaluvsException(ErrorCode.PASSWORD_EMPTY);
        }

        //유저 로그인 체크
        User user = userRepository.findByUsername(requestDto.getEmail()).orElseThrow(
                () -> new PlaluvsException(ErrorCode.USER_NOT_FOUND)
        );
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new PlaluvsException(ErrorCode.USER_NOT_FOUND);
        }
        String token = jwtTokenProvider.createToken(user.getUsername(), user.getUsername(), user.getNickname());

        SignInResponseDto signInResponseDto = new SignInResponseDto(
                token,
                user.getId(),
                user.getNickname(),
                (user.getGender() == null) ? Boolean.FALSE : Boolean.TRUE,
                (user.getAge() == null) ? Boolean.FALSE : Boolean.TRUE
        );

        return signInResponseDto;
    }
}
