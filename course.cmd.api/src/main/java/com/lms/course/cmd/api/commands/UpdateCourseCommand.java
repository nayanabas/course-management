package com.lms.course.cmd.api.commands;

import com.lms.course.core.models.Course;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class UpdateCourseCommand {
    @TargetAggregateIdentifier
    private String id;
    @NotNull(message = "no course details were supplied")
    @Valid
    private Course course;
}
