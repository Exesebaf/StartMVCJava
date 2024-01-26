package com.start.mvc.dto.responce;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class UpdatePasswordResponse {
    private String message;
}
