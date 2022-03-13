package com.zepto.irctc.dto;

import enums.Role;
import lombok.Data;

@Data
public class UserData {
    Integer id;
    String username;
    Role role;
}
