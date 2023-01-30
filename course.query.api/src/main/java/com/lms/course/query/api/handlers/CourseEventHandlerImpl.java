package com.lms.course.query.api.handlers;

import com.lms.course.core.events.CourseRegisteredEvent;
import com.lms.course.core.events.CourseRemovedEvent;
import com.lms.course.core.events.CourseUpdatedEvent;
import com.lms.course.query.api.repositories.CourseRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ProcessingGroup("course-group")
public class CourseEventHandlerImpl implements CourseEventHandler {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseEventHandlerImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @EventHandler
    @Override
    public void on(CourseRegisteredEvent event) {
        courseRepository.save(event.getCourse());
    }

    @EventHandler
    @Override
    public void on(CourseUpdatedEvent event) {
        courseRepository.save(event.getCourse());
        System.out.println("Course updated"+event.getCourse());
    }

    @EventHandler
    @Override
    public void on(CourseRemovedEvent event) {
        courseRepository.deleteById(event.getId());
    }
}
