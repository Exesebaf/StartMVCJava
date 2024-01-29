package com.start.mvc.util;

import com.start.mvc.dto.request.AuthUserRequest;
import com.start.mvc.dto.request.RegisterUserRequest;
import com.start.mvc.dto.request.UpdatePasswordRequest;
import com.start.mvc.dto.request.UpdateUserInfoRequest;
import org.springframework.stereotype.Component;


@Component
public class UserValidator {
    private static final int MIN_SIZE_NICKNAME = 3;
    private static final int MAX_SIZE_NICKNAME = 30;
    private static final int MIN_SIZE_EMAIL = 5;
    private static final int MAX_SIZE_EMAIL = 50;
    private static final int MIN_SIZE_FIRSTNAME = 2;
    private static final int MAX_SIZE_FIRSTNAME = 30;
    private static final int MIN_SIZE_PASSWORD = 3;
    private static final int MAX_SIZE_PASSWORD = 30;

    public boolean isValid(RegisterUserRequest userRequest) {
        return checkNickName(userRequest.getUserName())
                && checkFirstName(userRequest.getLastName())
                && checkEmail(userRequest.getEmail())
                && checkAge(userRequest.getAge())
                && checkPassword(userRequest.getPassword());
    }

    public boolean isValid(UpdateUserInfoRequest userRequest) {
        return checkNickName(userRequest.getUserName())
                && checkFirstName(userRequest.getLastName())
                && checkEmail(userRequest.getEmail())
                && checkAge(userRequest.getAge())
                && checkPassword(userRequest.getPassword());
    }

    public boolean isValid(UpdatePasswordRequest passRequest) {
        return checkNickName(passRequest.getUserName())
                && checkPassword(passRequest.getPassword())
                && checkPassword(passRequest.getNewPassword());
    }

    public boolean isValid(AuthUserRequest authRequest) {
        return checkNickName(authRequest.getUserName())
                && checkPassword(authRequest.getPassword());
    }

    //проверка ника пользователя
    private boolean checkNickName(String s) {
        return notNull(s) && minSize(s, MIN_SIZE_NICKNAME)
                && maxSize(s, MAX_SIZE_NICKNAME);
    }

    //проверка имени пользователя
    private boolean checkFirstName(String s) {
        return notNull(s) && minSize(s, MIN_SIZE_FIRSTNAME)
                && maxSize(s, MAX_SIZE_FIRSTNAME);
    }

    //проверка электронной почты пользователя
    private boolean checkEmail(String s) {
        return notNull(s) && minSize(s, MIN_SIZE_EMAIL)
                && maxSize(s, MAX_SIZE_EMAIL);
    }

    //проверка возраста пользователя
    private boolean checkAge(int age) {
        return isPositive(age);
    }

    //проверка пароля пользователя
    private boolean checkPassword(String s) {
        return notNull(s) && minSize(s, MIN_SIZE_PASSWORD)
                && maxSize(s, MAX_SIZE_PASSWORD);
    }

    private boolean notNull(String s) {
        return s.trim() != null;
    }

    private boolean minSize(String s, int minSize) {
        return minSize <= s.length();
    }

    private boolean maxSize(String s, int maxSize) {
        return maxSize >= s.length();
    }

    private boolean isPositive(int i) {
        return i > 0;
    }
}
