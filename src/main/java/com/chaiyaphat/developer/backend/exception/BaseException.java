package com.chaiyaphat.developer.backend.exception;

public abstract class BaseException extends Exception {
    public BaseException(String code){
        super(code);
    }
}
