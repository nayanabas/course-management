package com.lms.course.core.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "courses")
public class Course {
    @Id
    private String id;

    @NotEmpty(message = "coursename is mandatory")
    private String coursename;

    @NotEmpty(message = "technology is mandatory")
    private String technology;

    @NotEmpty(message = "duration is mandatory")
    private int duration;

    private String description;
}
