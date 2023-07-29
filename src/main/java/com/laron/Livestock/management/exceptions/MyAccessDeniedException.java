package com.laron.Livestock.management.exceptions;

public class MyAccessDeniedException extends RuntimeException{


    public MyAccessDeniedException(String message){
        super(message);
    }
}
