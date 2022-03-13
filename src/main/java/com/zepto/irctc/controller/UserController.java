package com.zepto.irctc.controller;

import com.zepto.irctc.dto.LoginCredentials;
import com.zepto.irctc.dto.UserData;
import com.zepto.irctc.dto.UserToRegister;
import com.zepto.irctc.exception.InvalidPasswordOrUserName;
import com.zepto.irctc.exception.InvalidRoleException;
import com.zepto.irctc.exception.UserAlreadyExistsException;
import com.zepto.irctc.model.User;
import com.zepto.irctc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@Validated
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/index")
    public ResponseEntity<String> isWorking(){
        return new ResponseEntity<String>("Application is running", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserData> login(@Valid @RequestBody LoginCredentials loginCredentials,
                                        HttpServletRequest httpServletRequest) throws InvalidPasswordOrUserName {
        HttpSession httpSession = httpServletRequest.getSession();
        Object obj = httpSession.getAttribute(UserService.LOGGED_IN);
        Boolean value = (obj == null) ? false : (Boolean)obj;
        User user ;
        if ( value ){
            // if the user is already logged, this request should be idempotent.
            user = (User) httpSession.getAttribute(userService.USER);
        }
        else {
            user = userService.login(loginCredentials, httpSession);
        }
        UserData userData = new UserData();
        userData.setUsername(user.getUsername());
        userData.setId(user.getId());
        userData.setRole(user.getRole());
        return new ResponseEntity<UserData>(userData, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserData> register(@RequestBody UserToRegister userToRegister) throws UserAlreadyExistsException,
            InvalidRoleException {
        User user = userService.register(userToRegister);
        UserData userData = new UserData();
        userData.setUsername(user.getUsername());
        userData.setId(user.getId());
        userData.setRole(user.getRole());
        return new ResponseEntity<UserData>(userData, HttpStatus.OK);
    }


}
