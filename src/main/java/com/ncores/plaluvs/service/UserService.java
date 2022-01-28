package com.ncores.plaluvs.service;

import com.ncores.plaluvs.controller.user.dto.*;
import com.ncores.plaluvs.domain.dto.ElementsDto;
import com.ncores.plaluvs.domain.skintype.SkinType;
import com.ncores.plaluvs.domain.user.User;
import com.ncores.plaluvs.domain.user.UserRoleEnum;
import com.ncores.plaluvs.exception.ErrorCode;
import com.ncores.plaluvs.exception.PlaluvsException;
import com.ncores.plaluvs.repository.skinType.SkinTypeRepository;
import com.ncores.plaluvs.repository.UserCosmeticRepository;
import com.ncores.plaluvs.repository.cosmetic.CosmeticRepository;
import com.ncores.plaluvs.repository.elements.ElementsRepository;
import com.ncores.plaluvs.repository.UserRepository;
import com.ncores.plaluvs.security.UserDetailsImpl;
import com.ncores.plaluvs.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    private final ElementsRepository elementsRepository;
    private final CosmeticRepository cosmeticRepository;
    private final UserCosmeticRepository userCosmeticRepository;
    private final SkinTypeRepository skinTypeRepository;


    @Transactional
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

        if(email.length() < 8){
            throw new PlaluvsException(ErrorCode.USERNAME_LENGTH_MIN);
        }

        Optional<User> found = userRepository.findByUsername(email);
        if (found.isPresent()) {
            throw new PlaluvsException(ErrorCode.USERNAME_DUPLICATE);
        }
    }

    private void nicknameCheck(String nickname) throws PlaluvsException{
        if (nickname.isEmpty()) {
            throw new PlaluvsException(ErrorCode.NICKNAME_EMPTY);
        }

        if(nickname.length() >= 8) {
            throw new PlaluvsException(ErrorCode.NICKNAME_LENGTH_MAX);
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

        if(pw.length() < 8 ){
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
        SkinType skinType = skinTypeRepository.findTopByUserOrderByCreatedAtDesc(user);


        SignInResponseDto signInResponseDto = new SignInResponseDto(
                token,
                user.getId(),
                user.getNickname(),
                (user.getGender() == null) ? Boolean.FALSE : Boolean.TRUE,
                (user.getAge() == null) ? Boolean.FALSE : Boolean.TRUE,
                getCurrentSKinStatusExist(skinType),
                getCurrentSKinWorryExist(skinType)
        );

        return signInResponseDto;
    }

    private Boolean getCurrentSKinStatusExist(SkinType skinType){
        if(skinType == null)
            return Boolean.FALSE;
        else{
            return (skinType.getCurrentSkinStatus() == null) ? Boolean.FALSE : Boolean.TRUE;
        }
    }

    private Boolean getCurrentSKinWorryExist(SkinType skinType){
        if(skinType == null)
            return Boolean.FALSE;
        else{
            return (skinType.getSkinTroubleList() == null) ? Boolean.FALSE : Boolean.TRUE;
        }
    }

    @Transactional
    public void setAge(PatchAgeRequestDto requestDto, UserDetailsImpl userDetails) throws PlaluvsException {
        LocalDate now = LocalDate.now();
        Long nowYear = Long.valueOf(now.getYear());

        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(
                () -> new PlaluvsException(ErrorCode.USER_NOT_FOUND)
        );

        if(requestDto.getAge() == null)
            throw new PlaluvsException(ErrorCode.DATA_EMPTY);
        if(requestDto.getAge() >= nowYear || requestDto.getAge() < 1920 )
            throw new PlaluvsException(ErrorCode.AGE_TYPE_NOT_SUITABLE);

        user.changeAge(requestDto.getAge());
    }

    @Transactional
    public void setGender(PatchGenderRequestDto requestDto, UserDetailsImpl userDetails) throws PlaluvsException {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(
                () -> new PlaluvsException(ErrorCode.USER_NOT_FOUND)
        );

        user.changeGender(requestDto.getGender());
    }

    public List<ElementsDto> getUserElements(UserDetailsImpl userDetails) throws PlaluvsException {
        UserDetailsImpl.UserCheck(userDetails);

        List<ElementsDto> result = elementsRepository.findAllByUserCustom(userDetails.getUser());
        for (ElementsDto elementsDto : result) {
           elementsDto.setImg(getImgSRc(elementsDto.getLevel()));
        }

        return result;
    }

    private String getImgSRc(String level) {
        String low = "https://plaluvs-image.s3.ap-northeast-2.amazonaws.com/rank/row_rank.png";
        String middle = "https://plaluvs-image.s3.ap-northeast-2.amazonaws.com/rank/middle_rank.png";
        String high = "https://plaluvs-image.s3.ap-northeast-2.amazonaws.com/rank/high_rank.png";

        if( level.contains("1")){
            return low;
        }
        else if( level.contains("2")){
            return low;
        }

        else if( level.contains("3")){
            return middle;
        }

        else if( level.contains("4")){
            return middle;
        }

        else if( level.contains("5")){
            return middle;
        }

        else if( level.contains("6")){
            return middle;
        }

        else if( level.contains("7")){
            return high;
        }

        else if( level.contains("8")){
            return high;
        }

        else if( level.contains("9")){
            return high;
        }

        else if( level.contains("10")){
            return high;
        }

        else if( level.contains("-")){
            return low;
        }

        return "";
    }

    public List<CosmeticDto> getUserCosmetics(UserDetailsImpl userDetails) throws PlaluvsException {
        UserDetailsImpl.UserCheck(userDetails);

        List<CosmeticDto> result = cosmeticRepository.findAllByUserCustom(userDetails.getUser());
        naverUrl(result);


        return result;
    }

    private void naverUrl(List<CosmeticDto> result) {
        for (CosmeticDto cosmeticDto : result){

            String name = cosmeticDto.getKorName();
            String brand = cosmeticDto.getBrandName();
            String url = "https://search.shopping.naver.com/search/all?frm=NVSHATC" +
                    "&origQuery=" + brand+name +
                    "&pagingIndex=1&pagingSize=40" +
                    "&productSet=total" +
                    "&query=" + brand+name +
                    "&sort=price_asc" +
                    "&timestamp=" +
                    "&viewType=list";

            cosmeticDto.setNaverUrl(url);
        }
    }

    public void deleteUser(UserDetailsImpl userDetails, DeleteUserRequestDto requestDto) throws PlaluvsException {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(
                () -> new PlaluvsException(ErrorCode.USER_NOT_FOUND)
        );

        if(requestDto.getPassword() == null){
            throw new PlaluvsException(ErrorCode.PASSWORD_EMPTY);
        }
        if(!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())){
            throw new PlaluvsException(ErrorCode.PASSWORD_NOT_EQAUL_DELETE);
        }

        userRepository.deleteById(user.getId());
    }

}
