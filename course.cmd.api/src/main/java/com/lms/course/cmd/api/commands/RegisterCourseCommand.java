package com.lms.course.cmd.api.commands;

import com.lms.course.core.models.Course;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class RegisterCourseCommand {
    @TargetAggregateIdentifier
    private String id;

    private Course course;
}
