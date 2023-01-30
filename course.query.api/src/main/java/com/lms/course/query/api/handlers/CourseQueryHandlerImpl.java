package com.lms.course.query.api.handlers;

import com.lms.course.query.api.repositories.CourseRepository;
import com.lms.course.query.api.dto.CourseLookupResponse;
import com.lms.course.query.api.queries.FindAllCoursesQuery;
import com.lms.course.query.api.queries.FindCourseByIdQuery;
import com.lms.course.query.api.queries.SearchCoursesQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseQueryHandlerImpl implements CourseQueryHandler {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseQueryHandlerImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @QueryHandler
    @Override
    public CourseLookupResponse getCourseById(FindCourseByIdQuery query) {
        var course = courseRepository.findById(query.getId());
        System.out.println("repo"+course.toString()+"------"+query.getId());
        return course.isPresent() ? new CourseLookupResponse(course.get()) : null;
    }

    @QueryHandler
    @Override
    public CourseLookupResponse searchCourses(SearchCoursesQuery query) {
        var courses = new ArrayList<>(courseRepository.findByFilterRegex(query.getFilter()));
        return new CourseLookupResponse(courses);
    }

    @QueryHandler
    @Override
    public CourseLookupResponse getAllCourses(FindAllCoursesQuery query) {
        var courses = new ArrayList<>(courseRepository.findAll());
        return new CourseLookupResponse(courses);
    }
}
