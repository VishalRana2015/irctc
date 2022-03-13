package com.zepto.irctc.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
public class UserToRegister {
    @NotNull
    String username;
    @NotNull
    String password;
    String role; // If role is empty, then the default role will be of User.
}
