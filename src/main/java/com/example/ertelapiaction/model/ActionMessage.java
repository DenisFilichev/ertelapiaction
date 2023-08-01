package com.example.ertelapiaction.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActionMessage {
    private String host;
    private String action;
    private String text;
    private String status;
}
