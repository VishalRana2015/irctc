package com.zepto.irctc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidCoacheTypeException extends Exception {
    public static String DEFAULT_MSG = "Invalid Type ";
    public InvalidCoacheTypeException(String msg){
        super(msg);
    }
}
