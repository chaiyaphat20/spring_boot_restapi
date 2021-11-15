package com.chaiyaphat.developer.backend.exception;

public class FileException extends Exception{
    public FileException(String code) {
        super("file."+code);
    }

    public static FileException fileNull(){
        return  new FileException("null");
    }


    public static FileException fileMaxSize(){
        return  new FileException("max.size");
    }

    public static FileException unsupported(){
        return  new FileException("unsupported.file");
    }
}
