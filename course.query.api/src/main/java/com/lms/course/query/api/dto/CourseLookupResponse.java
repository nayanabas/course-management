package com.lms.course.query.api.dto;

import com.lms.course.core.dto.BaseResponse;
import com.lms.course.core.models.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseLookupResponse extends BaseResponse {
    private List<Course> courses;

    public CourseLookupResponse(String message) {
        super(message);
    }

    public CourseLookupResponse(List<Course> courses) {
        super(null);
        this.courses = courses;
    }

    public CourseLookupResponse(String message, Course course) {
        super(message);
        this.courses = new ArrayList<>();
        this.courses.add(course);
    }

    public CourseLookupResponse(Course course) {
        super(null);
        this.courses = new ArrayList<>();
        this.courses.add(course);
    }

    public List<Course> getCourses() {
        return this.courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
