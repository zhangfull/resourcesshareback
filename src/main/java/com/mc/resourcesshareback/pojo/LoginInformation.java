package com.mc.resourcesshareback.pojo;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class LoginInformation {

    @Email(message = "email format")
    private String email;
    private String password;

}
