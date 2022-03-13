package com.zepto.irctc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AlreadyBookedCoachesException extends Exception{
    List<Integer> ids ;
    String msg;
    public AlreadyBookedCoachesException(List<Integer> list){
        StringBuilder builder = new StringBuilder();
        builder.append( "Bad Request : You tried to following already booked coaches : ");
        for ( int i =0; i < list.size(); i++){
            builder.append(list.get(0) + ", ");
        }
        msg = builder.toString();
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}
