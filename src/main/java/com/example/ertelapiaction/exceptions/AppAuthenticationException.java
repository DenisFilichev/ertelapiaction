package com.example.ertelapiaction.exceptions;

import org.springframework.security.core.AuthenticationException;

public class AppAuthenticationException extends AuthenticationException {
    public AppAuthenticationException(String msg) {
        super(msg);
    }
}
