package com.start.mvc.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserInfoRequest {
    private String userName;

    private String lastName;

    private String email;

    private int age;

    private String password;
}
