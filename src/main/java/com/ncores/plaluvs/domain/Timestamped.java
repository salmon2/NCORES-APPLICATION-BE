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
import java.util.Date;

@MappedSuperclass // Entity가 자동으로 컬럼으로 인식합니다.
@EntityListeners(AuditingEntityListener.class) // 생성/변경 시간을 자동으로 업데이트합니다.
@Getter
@Setter
public abstract class Timestamped {

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public static String TimeToString(LocalDateTime dateTime, String format){

        SimpleDateFormat format1 = new SimpleDateFormat(format);
        Date date = java.sql.Timestamp.valueOf(dateTime);


        String stringTime = format1.format(date);

        return stringTime;
    }

    public static String getCurrentTime(String format) {
        SimpleDateFormat format1 = new SimpleDateFormat (format);

        Date rawTime = new Date();

        String date = format1.format(rawTime);


        return date;
    }



}