package com.lms.course.cmd.api.security;

public interface PasswordEncoder {
    String hashPassword(String password);
}
