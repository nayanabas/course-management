package com.lms.course.query.api.repositories;

import com.lms.course.core.models.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CourseRepository extends MongoRepository<Course, String> {
    @Query("{'$or' : [{'coursename': {$regex : ?0}}, {'technology': {$regex : ?0}}]}")
    List<Course> findByFilterRegex(String filter);
}
