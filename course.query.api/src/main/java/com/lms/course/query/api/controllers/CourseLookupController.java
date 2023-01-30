package com.lms.course.query.api.controllers;

import com.lms.course.query.api.queries.FindAllCoursesQuery;
import com.lms.course.query.api.queries.FindCourseByIdQuery;
import com.lms.course.query.api.queries.SearchCoursesQuery;
import com.lms.course.query.api.dto.CourseLookupResponse;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/CourseLookup")
public class CourseLookupController {
    private final QueryGateway queryGateway;

    @Autowired
    public CourseLookupController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping(path = "/")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<CourseLookupResponse> getAllUsers() {
        try {
            var query = new FindAllCoursesQuery();
            var response = queryGateway.query(query, ResponseTypes.instanceOf(CourseLookupResponse.class)).join();

            if (response == null || response.getCourses() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get all Courses request";
            System.out.println(e.toString());

            return new ResponseEntity<>(new CourseLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/byId/{id}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<CourseLookupResponse> getCourseById(@PathVariable(value = "id") String id) {
        try {
            var query = new FindCourseByIdQuery(id);
            var response = queryGateway.query(query, ResponseTypes.instanceOf(CourseLookupResponse.class)).join();

            if (response == null || response.getCourses() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get course by ID request";
            System.out.println(e.toString());

            return new ResponseEntity<>(new CourseLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/byFilter/{filter}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<CourseLookupResponse> searchCourseByFilter(@PathVariable(value = "filter") String filter) {
        try {
            var query = new SearchCoursesQuery(filter);
            var response = queryGateway.query(query, ResponseTypes.instanceOf(CourseLookupResponse.class)).join();

            if (response == null || response.getCourses() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete course search request";
            System.out.println(e.toString());

            return new ResponseEntity<>(new CourseLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
