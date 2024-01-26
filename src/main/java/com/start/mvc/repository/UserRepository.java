package com.start.mvc.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import com.start.mvc.entity.User;


@Repository
public interface UserRepository {
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
    User findByNickname(String nickname);

    /**
     * Проверяет существует ли в базе пользователь стаким ником
     *
     * @param nickname ник пользователя
     * @return возвращает ответ true если в базе присутствует
     */
    boolean existsByNickname(String nickname);

    /**
     * Обновляет информацию о пользователе
     *
     * @param user пользователь (entity)
     * @return возвращает ответ true если в базе успешно изменили пользователя
     */
    boolean update(User user);
}
