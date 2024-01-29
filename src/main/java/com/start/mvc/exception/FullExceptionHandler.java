package com.start.mvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FullExceptionHandler {
    private final static int INVALID_REQUEST = 400;
    private final static int INCORRECT_PASSWORD = 401;
    private final static int NOT_FOUND = 404;
    private final static int CONFLICT = 409;

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErroreEntityResponse> handleInvalidRequestException(
            final InvalidRequestException e) {
        ErroreEntityResponse response = new ErroreEntityResponse(INVALID_REQUEST,
                "Неверный запрос. Убедитесь, что введенные вами данные " +
                        " верны, и повторите попытку" + e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<ErroreEntityResponse> handleIncorrectPasswordException(
            final IncorrectPasswordException e) {
        ErroreEntityResponse response = new ErroreEntityResponse(INCORRECT_PASSWORD,
                "Пароль был введен неправильно. " + e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErroreEntityResponse> handleUserNotFoundException(
            final UserNotFoundException e) {
        ErroreEntityResponse response = new ErroreEntityResponse(NOT_FOUND,
                "Пользователь не найден " + e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NickNameBusyException.class)
    public ResponseEntity<ErroreEntityResponse> handleNickNameBusyException(
            final NickNameBusyException e) {
        ErroreEntityResponse response = new ErroreEntityResponse(CONFLICT,
                "Этот Имя не верно. " + e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

}
