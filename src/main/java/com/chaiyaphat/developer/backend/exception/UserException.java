package com.chaiyaphat.developer.backend.exception;

public class UserException extends BaseException {
    public UserException(String code) {
        super("user." + code);
    }

    public static UserException requestNull() {
        return new UserException("register.request.null");
    }

    //user.register.email.null
    public static UserException emailNull() {
        return new UserException("register.email.null");
    }

    //User Not found
    public static UserException notFound() {
        return new UserException("user.not.found");
    }

    //CREATE
    public static UserException createEmailNull() {
        return new UserException("create.email.null");
    }

    public static UserException createPasswordNull() {
        return new UserException("create.password.null");
    }

    public static UserException createNameNull() {
        return new UserException("create.name.null");
    }

    public static UserException createEmailDuplicate() {
        return new UserException("create.email.duplicate");
    }

    //Login
    public static UserException loginFailEmailNotFound() {
        return new UserException("login.fail");
    }

    public static UserException loginFailPasswordInvalid() {
        return new UserException("login.fail");
    }
}
