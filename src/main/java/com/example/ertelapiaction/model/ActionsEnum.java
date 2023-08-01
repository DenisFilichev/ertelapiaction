package com.example.ertelapiaction.model;

import com.example.ertelapiaction.exceptions.ActionNotFoundException;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;
import java.util.Optional;

public enum ActionsEnum {
    OPEN_DOOR("opendoor"),
    OSD("changeosd");

    private final String text;

    ActionsEnum(String text) {
        this.text = text;
    }

    public String value(){
        return text;
    }

    public static ActionsEnum getByValue(String value){
        Optional<ActionsEnum> optional = Arrays.stream(ActionsEnum.values()).filter(act -> act.value().equals(value)).findFirst();
        if(optional.isPresent()){
            return optional.get();
        }else {
            throw new ActionNotFoundException("Operation '" +value + "' does not exist.");
        }
    }
}
