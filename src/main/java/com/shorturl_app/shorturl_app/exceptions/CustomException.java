package com.shorturl_app.shorturl_app.exceptions;

public class CustomException extends RuntimeException{



    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable error) {
        super(message, error);
    }


}
