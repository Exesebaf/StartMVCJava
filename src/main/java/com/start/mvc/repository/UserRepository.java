package com.start.mvc.repository;

import java.util.List;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.start.mvc.entity.User;


public interface UserRepository  {
    /**
     * Получение информации о пользователях постранично
     *
     * @param pageable информация для пагинации
     * @return возвращает список информации о пользователях
     */
    List<User> findAll(Pageable pageable);

    /**
     * Сохранение пользователя
     *
     * @param user информация о пользователе
     * @return возвращает ответ true если успешно сохранен в базу
     */
    boolean save(User user);

    /**
     * Получение информации о пользователе с nickname
     *
     * @param nickname ник пользователя
     * @return возвращает информацию о пользователе
     */
    User findByUsername(String nickname);

    /**
     * Проверяет существует ли в базе пользователь стаким ником
     *
     * @param nickname ник пользователя
     * @return возвращает ответ true если в базе присутствует
     */
    boolean existsByUsername(String nickname);

    /**
     * Проверяет существует ли в базе пользователь стаким эмейлом
     *
     * @param email email пользователя
     * @return возвращает ответ true если в базе присутствует
     */
    boolean existsByEmail(String email);

    /**
     * Обновляет информацию о пользователе
     *
     * @param user пользователь (entity)
     * @return возвращает ответ true если в базе успешно изменили пользователя
     */
    boolean update(User user);
}

