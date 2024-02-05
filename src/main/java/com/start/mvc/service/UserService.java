package com.start.mvc.service;

import com.start.mvc.dto.request.AuthUserRequest;
import com.start.mvc.dto.request.RegisterUserRequest;
import com.start.mvc.dto.request.UpdatePasswordRequest;
import com.start.mvc.dto.request.UpdateUserInfoRequest;
import com.start.mvc.dto.responce.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

        /**
         * Получение списка информации о пользователях постранично
         *
         * @param pageable информация для пагинации
         * @return возвращает информацию о пользователях постранично
         */
        Page<UserResponse> getUsers(Pageable pageable);

        /**
         * Регистрация нового пользователя в системе
         *
         * @param userRequest      информация о новом пользователе
         * @return возвращает ответ с информацией о новом пользователе
         */
        RegisterUserResponse registerNewUser(RegisterUserRequest userRequest);

        /**
         * Изменение информации о пользователе в системе
         *
         * @param updateInfoRequest новая информация о пользователе
         * @return возвращает ответ с новой информацией о пользователе
         */
        UpdateUserInfoResponse updateUser(UpdateUserInfoRequest updateInfoRequest);


        /**
         * Авторизация пользователя
         *
         * @param authUserRequest информация о пользователе для авторизации
         * @return возвращает ответ для авторизации пользователя
         */
        AuthUserResponse authUser(AuthUserRequest authUserRequest);

        /**
         * Изменение пароля конкретного пользователя
         *
         * @param updatePasswordRequest новая информация о пользователе
         * @return возвращает ответ что пароль изменен
         */
        UpdatePasswordResponse updateUser(UpdatePasswordRequest updatePasswordRequest);
}

