package com.example.ertelapiaction.web;

import com.example.ertelapiaction.model.*;
import com.example.ertelapiaction.services.ActionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/actions")
public class ActionController {

    private final ActionService actionService;

    @Autowired
    public ActionController(ActionService actionService) {
        this.actionService = actionService;
    }

    @Operation(
            summary = "Performing Device Operations",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Operation completed successfully",
                            content = @Content(schema = @Schema(implementation = ActionMessage.class))),
                    @ApiResponse(responseCode = "400",
                            description = "Wrong request format",
                            content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
                    @ApiResponse(responseCode = "404",
                            description = "Requested data not found",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @PostMapping(value = "/opendoor/{ip}")
    public ResponseEntity<ActionMessage> openDoor(@PathVariable String ip){
        System.out.println("/api/v1/actions/opendoor/" + ip);
        actionService.parseAction(new ActionModel(ip, ActionsEnum.OPEN_DOOR));
        return ResponseEntity.ok(
                ActionMessage.builder()
                        .host(ip)
                        .action(ActionsEnum.OPEN_DOOR.value())
                        .text("")
                        .status("OK")
                        .build()
        );
    }
}
