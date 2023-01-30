package com.lms.course.cmd.api.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
public class RemoveCourseCommand {
    @TargetAggregateIdentifier
    private String id;
}
