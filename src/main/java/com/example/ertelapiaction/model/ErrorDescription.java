package com.example.ertelapiaction.model;

import lombok.Data;

@Data
public class ErrorDescription {
    private final String field;
    private final String error;
}
