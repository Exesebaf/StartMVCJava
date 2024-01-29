package com.start.mvc.dto.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class AuthUserRequest {
    private String userName;

    private String password;
}
