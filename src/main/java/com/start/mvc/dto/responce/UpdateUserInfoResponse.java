package com.start.mvc.dto.responce;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class UpdateUserInfoResponse {
    private String userName;

    private String lastName;

    private String email;

    private int age;
}
