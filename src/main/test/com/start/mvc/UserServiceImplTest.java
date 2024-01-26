package com.start.mvc;

import com.start.mvc.dto.request.RegisterUserRequest;
import com.start.mvc.dto.responce.RegisterUserResponse;
import com.start.mvc.entity.User;
import com.start.mvc.exception.InvalidRequestException;
import com.start.mvc.mapper.UserMapper;
import com.start.mvc.repository.impl.UserRepositoryImpl;
import com.start.mvc.service.UserService;
import com.start.mvc.service.UserServiceImpl;
import com.start.mvc.util.UserValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserServiceImplTest {



        private UserService userService;

        public UserServiceImplTest() {
            this.userService = new UserServiceImpl(new UserRepositoryImpl(),
                    new UserMapper(), new UserValidator());
        }

    @Test
    void testRegisterNewUserThenAllOK1() {
        // Подготовка данных
        RegisterUserRequest userRequest = RegisterUserRequest.builder()
                .userName("Alisa")
                .lastName("Carrl")
                .email("catr.corap@gmail.com")
                .age(45)
                .password("scZsc")
                .build();

        RegisterUserResponse expectedResponse = RegisterUserResponse.builder()
                .userName("Alisa")
                .lastName("Carrl")
                .email("catr.corap@gmail.com")
                .age(45)
                .build();
    }




        @Test
        void testRegisterNewUserThenInvalidNickName() {
            RegisterUserRequest userRequest = RegisterUserRequest.builder()
                    .userName("H")
                    .lastName("Sema_li")
                    .email("xoy.li@mail.com")
                    .age(24)
                    .password("qwerty")
                    .build();
            assertThrows(InvalidRequestException.class,
                    () -> userService.registerNewUser(userRequest));
        }

        @Test
        void testRegisterNewUserThenInvalidFirstName() {
            RegisterUserRequest userRequest = RegisterUserRequest.builder()
                    .userName("Xana")
                    .lastName("S")
                    .email("xoy.li@mail.com")
                    .age(24)
                    .password("qwerty")
                    .build();
            assertThrows(InvalidRequestException.class,
                    () -> userService.registerNewUser(userRequest));
        }

        @Test
        void testRegisterNewUserThenInvalidEmail() {
            RegisterUserRequest userRequest = RegisterUserRequest.builder()
                    .userName("Sena")
                    .lastName("Serjo")
                    .email("se")
                    .age(35)
                    .password("qwerty")
                    .build();
            assertThrows(InvalidRequestException.class,
                    () -> userService.registerNewUser(userRequest));
        }

        @Test
        void testRegisterNewUserThenInvalidAge() {
            RegisterUserRequest userRequest = RegisterUserRequest.builder()
                    .userName("Sena")
                    .lastName("Serjo")
                    .email("xoy.li@mail.com")
                    .age(-10)
                    .password("qwerty")
                    .build();
            assertThrows(InvalidRequestException.class,
                    () -> userService.registerNewUser(userRequest));
        }

        @Test
        void testRegisterNewUserThenInvalidPassword() {
            RegisterUserRequest userRequest = RegisterUserRequest.builder()
                    .userName("Sena")
                    .lastName("Serjo")
                    .email("popop@mail.com")
                    .age(30)
                    .password("qw")
                    .build();
            assertThrows(InvalidRequestException.class,
                    () -> userService.registerNewUser(userRequest));
        }


}
