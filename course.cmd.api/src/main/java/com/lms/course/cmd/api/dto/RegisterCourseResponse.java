package com.lms.course.cmd.api.dto;

import com.lms.course.core.dto.BaseResponse;

public class RegisterCourseResponse extends BaseResponse {
    private String id;

    public RegisterCourseResponse(String id, String message) {
        super(message);
        this.id = id;
    }
}
