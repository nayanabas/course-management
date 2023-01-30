package com.lms.course.query.api.handlers;

import com.lms.course.core.events.CourseRegisteredEvent;
import com.lms.course.core.events.CourseRemovedEvent;
import com.lms.course.core.events.CourseUpdatedEvent;

public interface CourseEventHandler {
    void on(CourseRegisteredEvent event);
    void on(CourseUpdatedEvent event);
    void on(CourseRemovedEvent event);
}
