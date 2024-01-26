package com.start.mvc.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Данный класс основная сущность из которой мы берем параметры
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String userName;

    private String lastName;

    private String email;

    private int age;

    private String password;
}
