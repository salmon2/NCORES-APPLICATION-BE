package com.ncores.plaluvs.repository;

import com.ncores.plaluvs.domain.skintype.CurrentSkinStatus;
import com.ncores.plaluvs.domain.skintype.SkinType;
import com.ncores.plaluvs.domain.user.User;
import com.ncores.plaluvs.exception.ErrorCode;
import com.ncores.plaluvs.exception.PlaluvsException;
import com.ncores.plaluvs.security.UserDetailsImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface SkinTypeRepository extends JpaRepository<SkinType, Long> {
    SkinType findTopByUserOrderByCreatedAtAsc(User user);
    SkinType findTopByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    SkinType findTopByUserAndCreatedAtBetween(User user, LocalDateTime startDatetime, LocalDateTime endDatetime);

    SkinType findTopByUserOrderByCreatedAtDesc(User user);

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

    default SkinType findDailySkinTypeException(UserDetailsImpl userDetails) throws PlaluvsException {
        LocalDateTime startDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0,0)); //오늘 00:00:00
        LocalDateTime endDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59)); //오늘 23:59:59

        SkinType findDailySkinType = findTopByUserAndCreatedAtBetween(userDetails.getUser(), startDatetime, endDatetime);
        if(findDailySkinType == null)
            throw new PlaluvsException(ErrorCode.SKIN_TYPE_NOT_FOUND);
        return findDailySkinType;
    }


}
