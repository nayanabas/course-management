package com.lms.course.cmd.api.aggregates;

import com.lms.course.cmd.api.commands.RegisterCourseCommand;
import com.lms.course.cmd.api.commands.RemoveCourseCommand;
import com.lms.course.cmd.api.commands.UpdateCourseCommand;
import com.lms.course.cmd.api.security.PasswordEncoder;
import com.lms.course.cmd.api.security.PasswordEncoderImpl;
import com.lms.course.core.events.CourseRegisteredEvent;
import com.lms.course.core.events.CourseRemovedEvent;
import com.lms.course.core.events.CourseUpdatedEvent;
import com.lms.course.core.models.Course;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class CourseAggregate {
    @AggregateIdentifier
    private String id;
    private Course course;



    public CourseAggregate() {

    }

    @CommandHandler
    public CourseAggregate(RegisterCourseCommand command) {
        var newcourse = command.getCourse();
        newcourse.setId(command.getId());


        var event = CourseRegisteredEvent.builder()
                .id(command.getId())
                .course(newcourse)
                .build();

        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(UpdateCourseCommand command) {
        var updatedcourse = command.getCourse();
        updatedcourse.setId(command.getId());


        var event = CourseUpdatedEvent.builder()
                .id(UUID.randomUUID().toString())
                .course(updatedcourse)
                .build();

        System.out.println("command update"+event.getCourse());

        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(RemoveCourseCommand command) {
        var event = new CourseRemovedEvent();
        event.setId(command.getId());

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(CourseRegisteredEvent event) {
        this.id = event.getId();
        this.course = event.getCourse();
    }

    @EventSourcingHandler
    public void on(CourseUpdatedEvent event) {
        this.course = event.getCourse();
    }

    @EventSourcingHandler
    public void on(CourseRemovedEvent event) {
        AggregateLifecycle.markDeleted();
    }
}
