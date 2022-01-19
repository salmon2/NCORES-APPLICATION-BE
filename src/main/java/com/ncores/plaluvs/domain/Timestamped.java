package com.ncores.plaluvs.domain;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@MappedSuperclass // Entity가 자동으로 컬럼으로 인식합니다.
@EntityListeners(AuditingEntityListener.class) // 생성/변경 시간을 자동으로 업데이트합니다.
@Getter
@Setter
public abstract class Timestamped {

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public static String TimeToString(LocalDateTime dateTime, DateTimeFormatter dateTimeFormatter){

        String stringTime = dateTime.format(dateTimeFormatter);

        return stringTime;
    }

    public static List<String> getCurrentTime() {
        SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMdd");
        SimpleDateFormat format2 = new SimpleDateFormat ( "HH00");
        SimpleDateFormat format3 = new SimpleDateFormat ( "MM");
        SimpleDateFormat format4 = new SimpleDateFormat("yyyy.MM.dd HH:mm");

        Date rawTime = new Date();

        String date = format1.format(rawTime);
        String time = format2.format(rawTime);
        String month = format3.format(rawTime);
        String date2 = format4.format(rawTime);

        List<String> result = new ArrayList<>();

        result.add(date);
        result.add(time);
        result.add(month);
        result.add(date2);

        return result;
    }



}