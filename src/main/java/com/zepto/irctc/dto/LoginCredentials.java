package com.zepto.irctc.dto;


import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
@Validated
@Data
public class LoginCredentials {
    @NotNull
    String username;
    @NotNull
    String password;
}
