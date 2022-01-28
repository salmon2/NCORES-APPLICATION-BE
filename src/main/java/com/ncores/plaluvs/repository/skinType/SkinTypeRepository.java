package com.ncores.plaluvs.repository.skinType;

import com.ncores.plaluvs.domain.skintype.CurrentSkinStatus;
import com.ncores.plaluvs.domain.skintype.SkinType;
import com.ncores.plaluvs.domain.user.User;
import com.ncores.plaluvs.exception.ErrorCode;
import com.ncores.plaluvs.exception.PlaluvsException;
import com.ncores.plaluvs.security.UserDetailsImpl;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface SkinTypeRepository extends JpaRepository<SkinType, Long>, SkinTypeRepositoryCustom {
    SkinType findTopByUserOrderByCreatedAtAsc(User user);

    SkinType findTopByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    SkinType findTopByUserAndCreatedAtBetween(User user, LocalDateTime startDatetime, LocalDateTime endDatetime);

    SkinType findTopByUserOrderByCreatedAtDesc(User user);

    Boolean existsByUserAndCreatedAtBetween(User user, LocalDateTime start, LocalDateTime end);

    List<SkinType> findAllByUserOrderByCreatedAt(User user, Sort createdAt);

    SkinType findTopByCreatedAtBetweenAndUser(LocalDateTime start, LocalDateTime endDatetime, User user);


    default SkinType findDailySkinType(UserDetailsImpl userDetails) {
        LocalDateTime startDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0,0)); //오늘 00:00:00
        LocalDateTime endDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59)); //오늘 23:59:59

        SkinType findDailySkinType = findTopByUserAndCreatedAtBetween(userDetails.getUser(), startDatetime, endDatetime);

        SkinType firstSkinType = findTopByUserOrderByCreatedAtAsc(userDetails.getUser());
        CurrentSkinStatus currentSkinStatus = null;

        if(firstSkinType != null)
            currentSkinStatus = firstSkinType.getCurrentSkinStatus();

        if(findDailySkinType == null){
            SkinType newSkinType = new SkinType(currentSkinStatus, userDetails.getUser());
            save(newSkinType);
            return newSkinType;
        }
        else
            return findDailySkinType;

    }

    default SkinType findDailySkinTypeOrReturnNull(UserDetailsImpl userDetails) {
        LocalDateTime startDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0,0)); //오늘 00:00:00
        LocalDateTime endDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59)); //오늘 23:59:59

        SkinType findDailySkinType = findTopByUserAndCreatedAtBetween(userDetails.getUser(), startDatetime, endDatetime);

        return findDailySkinType;
    }

    default SkinType findDailySkinTypeException(User user) throws PlaluvsException {
        LocalDateTime startDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0,0)); //오늘 00:00:00
        LocalDateTime endDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59)); //오늘 23:59:59

        SkinType findDailySkinType = findTopByUserAndCreatedAtBetween(user, startDatetime, endDatetime);
        if(findDailySkinType == null)
            throw new PlaluvsException(ErrorCode.SKIN_TYPE_NOT_FOUND);
        return findDailySkinType;
    }

    default SkinType findDailySkinTypeOrLatestSkinType(UserDetailsImpl userDetails) throws PlaluvsException {
        LocalDateTime startDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0,0)); //오늘 00:00:00
        LocalDateTime endDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59)); //오늘 23:59:59

        SkinType findDailySkinType = findTopByUserAndCreatedAtBetween(userDetails.getUser(), startDatetime, endDatetime);

        if(findDailySkinType == null)
            findDailySkinType = findTopByUserOrderByCreatedAtAsc(userDetails.getUser());

        if(findDailySkinType == null)
            throw new PlaluvsException(ErrorCode.SKIN_TYPE_NOT_FOUND);

        return findDailySkinType;
    }

    default SkinType findDailySkinTypeOrLatestSkinTypeNotException(UserDetailsImpl userDetails) throws PlaluvsException {
        LocalDateTime startDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0,0)); //오늘 00:00:00
        LocalDateTime endDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59)); //오늘 23:59:59

        SkinType findDailySkinType = findTopByUserAndCreatedAtBetween(userDetails.getUser(), startDatetime, endDatetime);

        if(findDailySkinType == null)
            findDailySkinType = findTopByUserOrderByCreatedAtAsc(userDetails.getUser());


        return findDailySkinType;
    }

    default SkinType findDailySkinTypeOrLatestSkinType(User user) throws PlaluvsException {
        LocalDateTime startDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0,0)); //오늘 00:00:00
        LocalDateTime endDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59)); //오늘 23:59:59

        SkinType findDailySkinType = findTopByUserAndCreatedAtBetween(user, startDatetime, endDatetime);
        if(findDailySkinType == null)
            findDailySkinType = findTopByUserOrderByCreatedAtAsc(user);
        if(findDailySkinType == null)
            throw new PlaluvsException(ErrorCode.SKIN_TYPE_NOT_FOUND);

        return findDailySkinType;
    }


    default Boolean findDailyExists(User user){
        LocalDateTime startDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0,0)); //오늘 00:00:00
        LocalDateTime endDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59)); //오늘 23:59:59

        Boolean aBoolean = existsByUserAndCreatedAtBetween(user, startDatetime, endDatetime);
        return aBoolean;
    }


}
