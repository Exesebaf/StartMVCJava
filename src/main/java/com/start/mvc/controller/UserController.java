package com.start.mvc.controller;

import com.start.mvc.dto.request.AuthUserRequest;
import com.start.mvc.dto.request.RegisterUserRequest;
import com.start.mvc.dto.request.UpdateUserInfoRequest;
import com.start.mvc.dto.responce.AuthUserResponse;
import com.start.mvc.dto.responce.RegisterUserResponse;
import com.start.mvc.dto.responce.UpdateUserInfoResponse;
import com.start.mvc.dto.responce.UserResponse;
import com.start.mvc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/mvc")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;



    /**
     * Регистрация нового пользователя в системе
     * Данный метод регестрирует нового пользователя в систему он использует для этого userService
     * @param userRequest информация о новом пользователе
     * @return userRequest
     */
    @PostMapping
    public ResponseEntity<RegisterUserResponse> registerNewUser(
            @RequestBody RegisterUserRequest userRequest) {
        return ResponseEntity.ok(userService.registerNewUser(userRequest));
    }

    /**
     * Данный метод изменяет информацию пользователе в систему
     * @param updateInfoRequest новая информация о пользователе
     * @return updateInfoRequest
     */
    @PutMapping
    public ResponseEntity<UpdateUserInfoResponse> updateUser(
            @RequestBody UpdateUserInfoRequest updateInfoRequest) {
        return ResponseEntity.ok(userService.updateUser(updateInfoRequest));
    }

    /**
     * Данный метод позволяет получить постранично информациб о пользователях
     * @param page информация для пагинации
     * @param size информация для пагинации
     * @return возвращает информацию о пользователях постранично
     */
    @GetMapping
    public ResponseEntity<Page<UserResponse>> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        Pageable paging = PageRequest.of(page, size);
        return ResponseEntity.ok(userService.getUsers(paging));
    }
    /**
     * Данный метод позволяет авторизовать пользователя исопльзуя для это AuthUserResponse
     *
     * @param authUserRequest информация о пользователе для авторизации
     * @return authUserRequest
     */
    @PostMapping("/authUser")
    public ResponseEntity<AuthUserResponse> authUser(
            @RequestBody AuthUserRequest authUserRequest) {
        return ResponseEntity.ok(userService.authUser(authUserRequest));
    }
}
