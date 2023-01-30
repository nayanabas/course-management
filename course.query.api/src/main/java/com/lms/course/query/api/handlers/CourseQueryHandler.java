package com.lms.course.query.api.handlers;

import com.lms.course.query.api.dto.CourseLookupResponse;
import com.lms.course.query.api.queries.FindAllCoursesQuery;
import com.lms.course.query.api.queries.FindCourseByIdQuery;
import com.lms.course.query.api.queries.SearchCoursesQuery;

public interface CourseQueryHandler {
    CourseLookupResponse getCourseById(FindCourseByIdQuery query);
    CourseLookupResponse searchCourses(SearchCoursesQuery query);
    CourseLookupResponse getAllCourses(FindAllCoursesQuery query);
}
