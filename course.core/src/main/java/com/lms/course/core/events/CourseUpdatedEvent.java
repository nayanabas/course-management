package com.lms.course.core.events;

import com.lms.course.core.models.Course;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseUpdatedEvent {
    private String id;
    private Course course;
}
