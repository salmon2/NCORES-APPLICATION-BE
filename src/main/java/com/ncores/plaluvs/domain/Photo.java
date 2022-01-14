package com.ncores.plaluvs.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ncores.plaluvs.domain.user.User;
import com.ncores.plaluvs.exception.ErrorCode;
import com.ncores.plaluvs.exception.PlaluvsException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Photo extends Timestamped {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    User user;

    private String original_file_name;

    private String stored_file_path;

    public static void FileCheck(MultipartFile file) throws PlaluvsException {
        if(file.isEmpty()) {
            throw new PlaluvsException(ErrorCode.DATA_EMPTY);
        }
    }

    public Photo(User user, String original_file_name, String stored_file_path) {
        this.user = user;
        this.original_file_name = original_file_name;
        this.stored_file_path = stored_file_path;
    }
}
