package com.example.ertelapiaction.Dispatcher;

import com.example.ertelapiaction.model.ActionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActionDicpatcher {
    private final SenderByHost sender;

    @Autowired
    public ActionDicpatcher(SenderByHost sender) {
        this.sender = sender;
    }

    public void execute(ActionModel actionModel){
        switch (actionModel.getAction()){
            case OPEN_DOOR -> System.out.println(sender.open(actionModel.getIp()));
            case OSD -> System.out.println("OSD");
        }
    }
}
