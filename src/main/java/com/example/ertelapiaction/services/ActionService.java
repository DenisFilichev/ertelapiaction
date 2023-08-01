package com.example.ertelapiaction.services;

import com.example.ertelapiaction.Dispatcher.ActionDicpatcher;
import com.example.ertelapiaction.model.ActionModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class ActionService {
    private final ActionDicpatcher dicpatcher;

    @Autowired
    public ActionService(ActionDicpatcher dicpatcher) {
        this.dicpatcher = dicpatcher;
    }

    public void parseAction(@Valid ActionModel actionModel){
        dicpatcher.execute(actionModel);
    }
}
