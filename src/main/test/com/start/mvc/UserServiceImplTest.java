package com.start.mvc;

import com.start.mvc.exception.InvalidRequestException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import com.start.mvc.repository.impl.UserRepositoryByJPA;
import com.start.mvc.entity.User;
import com.start.mvc.dto.responce.RegisterUserResponse;
import com.start.mvc.dto.request.RegisterUserRequest;
import com.start.mvc.service.UserService;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
@ComponentScan(basePackages = "com.start.mvc")
public class UserServiceImplTest {



    @Autowired
    private UserService userService;

    @Autowired
    private UserRepositoryByJPA userRepository;






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

    @Test
    void testRegisterNewUserThenAllOK1() {
        RegisterUserRequest userRequest = RegisterUserRequest.builder()
                .userName("Alisa")
                .lastName("Carrl")
                .email("catr.corap@gmail.com")
                .age(45)
                .password("scZsc")
                .build();
        User userFind = userRepository.findByUserName(userRequest.getUserName());
        if(userFind!=null) {
            userRepository.delete(userFind);
        }
        RegisterUserResponse userInDataActual = userService.registerNewUser(userRequest);

        RegisterUserResponse expectedResponse = RegisterUserResponse.builder()
                .userName("Alisa")
                .lastName("Carrl")
                .email("catr.corap@gmail.com")
                .age(45)
                .build();
        assertEquals(expectedResponse, userInDataActual);
    }


}
