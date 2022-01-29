package com.ncores.plaluvs;


import com.ncores.plaluvs.controller.skin.dto.SkinDailySefCheckRequestDto;
import com.ncores.plaluvs.controller.skin.dto.SkinDailyStatusRequestDto;
import com.ncores.plaluvs.controller.skin.dto.SkinDailyStimulationRequestDto;
import com.ncores.plaluvs.crawling.ReadJsonFile;
import com.ncores.plaluvs.domain.dto.SkinNowStatusRequestDto;
import com.ncores.plaluvs.domain.dto.SkinWorryRequestDto;
import com.ncores.plaluvs.domain.skintype.SkinType;
import com.ncores.plaluvs.domain.user.User;
import com.ncores.plaluvs.domain.user.UserRoleEnum;
import com.ncores.plaluvs.exception.PlaluvsException;
import com.ncores.plaluvs.repository.CategoryRepository;
import com.ncores.plaluvs.repository.skinType.SkinTypeRepository;
import com.ncores.plaluvs.repository.UserRepository;
import com.ncores.plaluvs.service.SkinService;
import com.ncores.plaluvs.service.SkinTypeService;
import com.ncores.plaluvs.service.UserService;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@NoArgsConstructor
//@Component
public class SimpleListener implements ApplicationListener<ApplicationStartedEvent> {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ReadJsonFile readJsonFile;
    @Autowired
    private SkinTypeRepository skinTypeRepository;

    @Autowired
    private SkinService skinService;

    @Autowired
    private SkinTypeService skinTypeService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserService userService;


    @SneakyThrows
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        //Long id = initAndCreatedUser(593044L);
        //makeData(id);


//        for (int i = 0; i < 20; i++) {
////            initAndCreatedUser2(i);
////        }


        FiveWeekago(594902L);
        TWOWeekago(594902L);
        Weekago(594902L);
        yesterday(594902L);

    }
    private void TWOWeekago(Long id) throws PlaluvsException {
        Random rand = new Random();
        LocalDateTime newNow1  = LocalDateTime.of(LocalDate.now().minusWeeks(2).minusDays(1), LocalTime.of(12,12,12));

        List<LocalDateTime> list = new ArrayList<>();
        list.add(newNow1);

        User findUser = userRepository.findById(id).get();

        makeData(rand, list, findUser);
    }

    private void FiveWeekago(Long id) throws PlaluvsException {
        Random rand = new Random();

        LocalDateTime newNow1  = LocalDateTime.of(LocalDate.now().minusWeeks(5).minusDays(1), LocalTime.of(12,12,12));

        List<LocalDateTime> list = new ArrayList<>();
        list.add(newNow1);

        User findUser = userRepository.findById(id).get();

        makeData(rand, list, findUser);
    }

    private void Weekago(Long id)throws PlaluvsException{
        Random rand = new Random();

        LocalDateTime newNow1  = LocalDateTime.of(LocalDate.now().minusWeeks(1).minusDays(1), LocalTime.of(12,12,12));

        List<LocalDateTime> list = new ArrayList<>();
        list.add(newNow1);

        User findUser = userRepository.findById(id).get();

        makeData(rand, list, findUser);
    }

    private void yesterday(Long id)throws PlaluvsException{
        Random rand = new Random();

        LocalDateTime newNow1  = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(12,12,12));

        List<LocalDateTime> list = new ArrayList<>();
        list.add(newNow1);

        User findUser = userRepository.findById(id).get();

        makeData(rand, list, findUser);
    }


    private void makeData(Long id) throws PlaluvsException {
        Random rand = new Random();
        //initAndCreatedUser();
        LocalDateTime newNow1  = LocalDateTime.of(LocalDate.now().minusMonths(3), LocalTime.of(12,12,12));
        LocalDateTime newNow2  = LocalDateTime.of(LocalDate.now().minusMonths(2), LocalTime.of(12,12,12));
        LocalDateTime newNow3  = LocalDateTime.of(LocalDate.now().minusWeeks(2), LocalTime.of(12,12,12));
        LocalDateTime newNow4  = LocalDateTime.of(LocalDate.now().minusWeeks(2).minusDays(5), LocalTime.of(12,12,12));
        LocalDateTime newNow5  = LocalDateTime.of(LocalDate.now().minusWeeks(1), LocalTime.of(12,12,12));
        LocalDateTime newNow6  = LocalDateTime.of(LocalDate.now().minusDays(5), LocalTime.of(12,12,12));

        List<LocalDateTime> list = new ArrayList<>();
        list.add(newNow1);
        list.add(newNow2);
        list.add(newNow3);
        list.add(newNow4);
        list.add(newNow5);
        list.add(newNow6);

        User findUser = userRepository.findById(id).get();

        makeData(rand, list, findUser);
    }

    private void makeData(Random rand, List<LocalDateTime> list, User findUser) throws PlaluvsException {
        int i = 0;
        for (LocalDateTime localDateTime : list) {

            SkinNowStatusRequestDto skinNowStatus = new SkinNowStatusRequestDto(  Long.valueOf(rand.nextInt(5) + 1)  );

            List<Long> skinWorryId = new ArrayList<>();
            Long skinWorryId1 = Long.valueOf(rand.nextInt(5) + 1);
            skinWorryId.add(skinWorryId1);
            skinWorryId.add( randomId(skinWorryId1)    );
            SkinWorryRequestDto skinWorry = new SkinWorryRequestDto(   skinWorryId  );

            List<Long> skinDailyStatusId = new ArrayList<>();
            Long skinDailyStatusId1 =    Long.valueOf(rand.nextInt(5) + 1);
            skinDailyStatusId.add(skinDailyStatusId1);
            SkinDailyStatusRequestDto skinDailyStatus = new SkinDailyStatusRequestDto(skinDailyStatusId);


            List<Long> skinDailyStimulationId = new ArrayList<>();
            Long a = Long.valueOf(rand.nextInt(5) + 1);
            skinDailyStimulationId.add(a);
            skinDailyStimulationId.add(randomId(a));
            SkinDailyStimulationRequestDto skinDailyStimulation = new SkinDailyStimulationRequestDto(skinDailyStimulationId);


            SkinDailySefCheckRequestDto skinDailySefCheckRequestDto = new SkinDailySefCheckRequestDto((double)Math.round(Math.random()* 5));

            if(i == 0){
                skinService.currentSkinStatus(skinNowStatus, findUser, localDateTime);
                skinService.skinWorryUpdate(skinWorry, findUser, localDateTime);
            }
            skinService.skinDailyStatus(skinDailyStatus, findUser, localDateTime);
            skinService.skinDailyStimulation(skinDailyStimulation, findUser, localDateTime);
            skinService.skinSelfCheck(skinDailySefCheckRequestDto, findUser, localDateTime);
            skinService.skinBoumanCalucluate(findUser, localDateTime);
            skinTypeService.findSkinElements(findUser, localDateTime);
            i++;
        }
    }

    private Long randomId(Long id){
        Random rand = new Random();
        Long getId = Long.valueOf(rand.nextInt(5) + 1);
        while( !getId.equals(id) ){
            getId = Long.valueOf(rand.nextInt(5) + 1);
        }
        return getId;
    }

    private Long initAndCreatedUser(Long id) {
        //userRepository.deleteAll();
        userRepository.deleteById(id);

        User newUser2 = new User(
                "dys04076@naver.com",
                passwordEncoder.encode("asdf"),
                "test",
                UserRoleEnum.ADMIN
        );

        User save = userRepository.save(newUser2);
        return save.getId();

//        User newUser1 = new User(
//                "asdf1234",
//                passwordEncoder.encode("asdf1234"),
//                "test",
//                UserRoleEnum.ADMIN
//        );
//
//        userRepository.save(newUser1);

    }

    private Long initAndCreatedUser2(int i) {

        User newUser2 = new User(
                "admin" + i,
                passwordEncoder.encode("admin" + i),
                "test",
                UserRoleEnum.ADMIN
        );

        User save = userRepository.save(newUser2);
        return save.getId();

    }

}