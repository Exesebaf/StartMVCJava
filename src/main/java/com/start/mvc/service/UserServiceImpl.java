package com.start.mvc.service;

import com.start.mvc.dto.request.AuthUserRequest;
import com.start.mvc.dto.request.RegisterUserRequest;
import com.start.mvc.dto.request.UpdatePasswordRequest;
import com.start.mvc.dto.request.UpdateUserInfoRequest;
import com.start.mvc.dto.responce.*;
import com.start.mvc.entity.User;
import com.start.mvc.exception.IncorrectPasswordException;
import com.start.mvc.exception.InvalidRequestException;
import com.start.mvc.exception.NickNameBusyException;
import com.start.mvc.exception.UserNotFoundException;
import com.start.mvc.mapper.UserMapper;
import com.start.mvc.repository.UserRepository;
import com.start.mvc.util.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

        private final UserRepository userRepository;

        private final UserMapper mapper;

        private final UserValidator validator;



        /**
         * Данный метод регестрирует нового пользователя системы
         * И обрабатывает некоторые ошибки throw new NickNameBusyException(userName)
         * throw new InvalidRequestException("Invalid data request")
         * @param userRequest информация о новом пользователе
         * @return возвращает ответ с информацией о новом пользователе
         */
        @Override
        public RegisterUserResponse registerNewUser(RegisterUserRequest userRequest) {
            String userName = userRequest.getUserName();
            String email = userRequest.getEmail();
            User user;
            if (validator.isValid(userRequest)) {
                user = mapper.userRequestToUser(userRequest);
                if (userRepository.existsByUsername(userName)
                        || userRepository.existsByEmail(email)) {
                    throw new NickNameBusyException(userName);
                } else {
                    userRepository.save(user);
                }
            } else {
                throw new InvalidRequestException("Invalid data request");
            }
            return mapper.userToRegisterUserResponse(user);
        }

        /**
         * Данный метод позволяет изменить пороль пользователя на новый
         *
         * @param updatePasswordRequest новая информация о пользователе
         * @return возвращает ответ что пароль изменен
         */
        @Override
        public UpdatePasswordResponse updateUser(
                UpdatePasswordRequest updatePasswordRequest) {
            String nickName = updatePasswordRequest.getUserName();
            String pass = updatePasswordRequest.getPassword();
            String newPass = updatePasswordRequest.getNewPassword();
            if (validator.isValid(updatePasswordRequest)) {
                if (userRepository.existsByUsername(nickName)) {
                    User user = userRepository.findByUsername(nickName);
                    if (user.getPassword().equals(pass)) {
                        user.setPassword(newPass);
                        userRepository.update(user);
                        return new UpdatePasswordResponse("Password updated");
                    } else {
                        throw new IncorrectPasswordException(nickName);
                    }
                } else {
                    throw new UserNotFoundException(nickName);
                }
            } else {
                throw new InvalidRequestException("Invalid data request");
            }
        }

        /**
         * Данный метод обрабатывает изменения пользователя в системе
         *
         * @param updateInfoRequest новая информация о пользователе
         * @return возвращает обновленного пользователя
         */
        @Override
        public UpdateUserInfoResponse updateUser(UpdateUserInfoRequest updateInfoRequest) {
            String nickName = updateInfoRequest.getUserName();
            String email = updateInfoRequest.getEmail();
            String pass = updateInfoRequest.getPassword();
            if (validator.isValid(updateInfoRequest)) {
                if (userRepository.existsByUsername(nickName)
                        || userRepository.existsByEmail(email)) {
                    User user = userRepository.findByUsername(nickName);
                    if (user.getPassword().equals(pass)) {
                        user.setLastName(updateInfoRequest.getLastName());
                        user.setEmail(updateInfoRequest.getEmail());
                        user.setAge(updateInfoRequest.getAge());
                        userRepository.update(user);
                        return UpdateUserInfoResponse.builder()
                                .userName(updateInfoRequest.getUserName())
                                .lastName(updateInfoRequest.getLastName())
                                .email(updateInfoRequest.getEmail())
                                .age(updateInfoRequest.getAge())
                                .build();
                    } else {
                        throw new IncorrectPasswordException(nickName);
                    }
                } else {
                    throw new UserNotFoundException(nickName);
                }
            } else {
                throw new InvalidRequestException("Invalid data request");
            }
        }

        /**
         * Метод позволяет авторизовать пользователя в систему
         *
         * @param authUserRequest информация о пользователе для авторизации
         * @return возвращает ответ для авторизации пользователя
         */
        @Override
        public AuthUserResponse authUser(AuthUserRequest authUserRequest) {
            String nickName = authUserRequest.getUserName();
            String pass = authUserRequest.getPassword();
            if (validator.isValid(authUserRequest)) {
                if (userRepository.existsByUsername(nickName)){
                    User user = userRepository.findByUsername(nickName);
                    if (user.getPassword().equals(pass)) {
                        return AuthUserResponse.builder()
                                .secret("user")
                                .jwt(UUID.randomUUID().toString())
                                .build();
                    } else {
                        throw new IncorrectPasswordException(nickName);
                    }
                } else {
                    throw new UserNotFoundException(nickName);
                }
            } else {
                throw new InvalidRequestException("Invalid data request");
            }
        }
    /**
     * получени инфо о всех пользователях постранично
     * @param pageable информация для пагинации
     * @return возращает страницы с пользователем
     */
    @Override
    public Page<UserResponse> getUsers(Pageable pageable) {
        List<User> users = userRepository.findAll(pageable);
        List<UserResponse> usersResponse = new ArrayList<>();
        for (User u : users) {
            usersResponse.add(mapper.userToUserResponse(u));
        }
        return new PageImpl(usersResponse);
    }

    @Autowired
    public UserServiceImpl(
            @Qualifier(value = "userRepositoryByHibernete")
            UserRepository userRepository,
            UserMapper mapper, UserValidator validator) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.validator = validator;
    }
}
