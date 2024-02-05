package com.start.mvc.mapper;

import com.start.mvc.dto.request.RegisterUserRequest;
import com.start.mvc.dto.request.UpdateUserInfoRequest;
import com.start.mvc.dto.responce.RegisterUserResponse;
import com.start.mvc.dto.responce.UpdateUserInfoResponse;
import com.start.mvc.dto.responce.UserResponse;
import com.start.mvc.entity.User;
import org.springframework.stereotype.Component;
/**
 * В основном это перегоньщик User для защиты данный в другом случае надо было бы использовать Mapstract
 * в пользователя (entity)
 *
 */
@Component
public class UserMapper {
    /**
     * Мапинг запроса с информацией о новом пользователе
     * в пользователя (entity)
     *
     * @param userRequest информация о новом пользователе
     * @return возвращает пользователя (entity)
     */
    public User userRequestToUser(RegisterUserRequest userRequest) {
        return User.builder()
                .userName(userRequest.getUserName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .age(userRequest.getAge())
                .password(userRequest.getPassword())
                .build();
    }

    /**
     * Мапинг информации о пользователе (entity)
     * в ответ о новом пользователе
     *
     * @param user информация о новом пользователе (entity)
     * @return возвращает ответ о новом пользователе
     */
    public RegisterUserResponse userToRegisterUserResponse(User user) {
        return RegisterUserResponse.builder()
                .userName(user.getUserName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .age(user.getAge())
                .build();
    }

    /**
     * Мапинг информации о пользователе (entity)
     * в ответ о пользователе
     *
     * @param user информация о новом пользователе (entity)
     * @return возвращает ответ о пользователе
     */
    public UserResponse userToUserResponse(User user) {
        return UserResponse.builder()
                .userName(user.getUserName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .age(user.getAge())
                .build();
    }

    /**
     * Мапинг обновленной информации о пользователе
     * в пользователя (entity)
     *
     * @param updateUserRequest обновленная информация о пользователе
     * @return возвращает пользователя с обновленной информацией (entity)
     */
    public User updateUserRequestToUser(
            UpdateUserInfoRequest updateUserRequest) {
        return User.builder()
                .lastName(updateUserRequest.getLastName())
                .email(updateUserRequest.getEmail())
                .age(updateUserRequest.getAge())
                .build();
    }

    /**
     * Мапинг пользователя (entity)
     * в ответ об обновленнии информации пользователя
     *
     * @param user информация о пользователе (entity)
     * @return возвращает пользователя с обновленной информацией
     */
    public UpdateUserInfoResponse userToUpdateUserResponse(User user) {
        return UpdateUserInfoResponse.builder()
                .userName(user.getUserName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .age(user.getAge())
                .build();
    }
}
