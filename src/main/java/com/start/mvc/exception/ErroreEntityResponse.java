package com.start.mvc.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErroreEntityResponse {

    private int httpCode;
    private String debugMessage;


}
