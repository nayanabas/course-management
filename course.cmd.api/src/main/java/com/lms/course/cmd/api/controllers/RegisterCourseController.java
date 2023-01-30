package com.lms.course.cmd.api.controllers;

import com.lms.course.cmd.api.commands.RegisterCourseCommand;
import com.lms.course.cmd.api.dto.RegisterCourseResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/registerCourse")
public class RegisterCourseController {
    private final CommandGateway commandGateway;

    @Autowired
    public RegisterCourseController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<RegisterCourseResponse> registerCourse(@Valid @RequestBody RegisterCourseCommand command) {
        var id = UUID.randomUUID().toString();
        command.setId(id);

        try {
            commandGateway.send(command);
            System.out.println("registerd course");

            return new ResponseEntity<>(new RegisterCourseResponse(id, "Course successfully registered!"), HttpStatus.CREATED);
        } catch (Exception e) {
            var safeErrorMessage = "Error while processing register register request for id - " + id;
            System.out.println(e.toString());

            return new ResponseEntity<>(new RegisterCourseResponse(id, safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
