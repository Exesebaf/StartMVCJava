package com.start.mvc.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class UpdateUserInfoRequest {
    private String userName;

    private String lastName;

    private String email;

    private int age;

    private String password;
}
