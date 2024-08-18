package com.newspulse.exception;

import org.springframework.http.HttpStatusCode;

public class UserLevelException extends Exception {
    public UserLevelException(String message) {
        super(message);
    }
}
