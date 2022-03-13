package com.zepto.irctc.service;

import com.zepto.irctc.dto.LoginCredentials;
import com.zepto.irctc.dto.UserToRegister;
import com.zepto.irctc.exception.InvalidPasswordOrUserName;
import com.zepto.irctc.exception.InvalidRoleException;
import com.zepto.irctc.exception.UserAlreadyExistsException;
import com.zepto.irctc.model.User;
import com.zepto.irctc.repository.UserRepository;
import enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    private static String INVALID_USERNAME_PASSWORD_MSG = "Invalid username or password.";
    private static String USER_ALREADY_EXISTS_MSG = "User Already Exists";
    private static String INVALID_ROLE_MSG = "Invalid Role.";
    public static String LOGGED_IN = "loggedIn";
    public static String USER = "User";
    public User login (LoginCredentials credentials, HttpSession session) throws InvalidPasswordOrUserName{
        Optional<User> optionalUser = userRepository.findUserByUsername(credentials.getUsername());
        if ( optionalUser.isEmpty()){
            throw new InvalidPasswordOrUserName(INVALID_USERNAME_PASSWORD_MSG);
        }
        User user = optionalUser.get();
        if ( !user.getPassword().equals(credentials.getPassword())){
            throw new InvalidPasswordOrUserName(INVALID_USERNAME_PASSWORD_MSG);
        }
        // Since the credentials matches,
        session.setAttribute(USER, user);
        session.setAttribute(LOGGED_IN, true);
        return user;
    }


    public User register( UserToRegister userToRegister) throws UserAlreadyExistsException, InvalidRoleException {
        Optional<User> optionalUser = userRepository.findUserByUsername(userToRegister.getUsername());
        if (!optionalUser.isEmpty()){
            throw new UserAlreadyExistsException(USER_ALREADY_EXISTS_MSG);
        }
        Role role = getRole(userToRegister.getRole());
        User user = new User();
        user.setUsername(userToRegister.getUsername());
        user.setPassword(userToRegister.getPassword());
        user.setRole(role);
        userRepository.save(user);
        return user;
    }

    private Role getRole(String role) throws InvalidRoleException{
        if (role != null &&  "admin".equals(role.toLowerCase())){
            return Role.ADMIN;
        }
        if (role == null || "user".equals( role.toLowerCase(Locale.ROOT))){
            return Role.USER;
        }

        throw new InvalidRoleException(INVALID_ROLE_MSG);
    }
}
