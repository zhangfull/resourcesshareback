package com.mc.resourcesshareback.pojo;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class User {

    private Long id;
    private String username;
    @Email(message = "email format")
    private String email;
    private String password;
    private String avatarUrl;
    private LocalDateTime registerDate;
    private LocalDateTime lastLoginDate;
    private LocalDateTime updateDate;

    private Long followersCount;

}
