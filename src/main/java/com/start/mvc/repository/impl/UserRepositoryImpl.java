package com.start.mvc.repository.impl;

import java.util.*;
import javax.annotation.PostConstruct;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import com.start.mvc.entity.User;
import com.start.mvc.repository.UserRepository;

/**
 * Класс для работы с базой данных с пользователями
 */
@Repository("userRepositoryImpl")
public class UserRepositoryImpl  implements UserRepository{

    private final Map<String, User> users = new HashMap<>();

    /**
     * Получение информации о пользователях постранично
     *
     * @param pageable информация для пагинации
     * @return возвращает список информации о пользователях
     */

    public List<User> findAll(Pageable pageable) {
        Collection<User> values = users.values();
        return new ArrayList<>(values);
    }

    /**
     * Сохранение пользователя
     *
     * @param user информация о пользователе
     * @return возвращает ответ true если успешно сохранен в базу
     */

    public boolean save(User user) {
        users.put(user.getUserName(), user);
        User userInData = users.get(user.getUserName());
        return userInData.equals(user);
    }

    /**
     * Получение информации о пользователе с nickname
     *
     * @param nickname ник пользователя
     * @return возвращает информацию о пользователе
     */

    public User findByUsername(String nickname) {
        return users.get(nickname);
    }

    /**
     * Проверяет существует ли в базе пользователь стаким ником
     *
     * @param nickname ник пользователя
     * @return возвращает ответ true если в базе присутствует
     */

    public boolean existsByUsername(String nickname) {
        return users.containsKey(nickname);
    }
    /**
     * Проверяет существует ли в базе пользователь стаким email
     *
     * @param email email пользователя
     * @return возвращает ответ true если в базе присутствует
     */
    @Override
    public boolean existsByEmail(String email) {
        return false;
    }

    /**
     * Обновляет информацию о пользователе
     *
     * @param user пользователь (entity)
     * @return возвращает ответ true если в базе успешно изменили пользователя
     */
    public boolean update(User user) {
        users.remove(user.getUserName());
        users.put(user.getUserName(), user);
        User userInData = users.get(user.getUserName());
        return userInData.equals(user);
    }


//    блок инициализации
    @SuppressWarnings("checkstyle:MagicNumber")
    @PostConstruct
    public void init() {
        User user1 = new User(
               1, "Zay", "May", "may.bulochka@mail.com", 13, "asd123123");
        User user2 = new User(
               2, "Kama", "Danil", "danil.killer@mail.com", 12, "as1234");
        User user3 = new User(
               3, "Sali", "Will", "will@mail.com", 19, "aa1235655");
        User user4 = new User(
                4,"NonTen", "Alena", "alena@mail.com", 27, "zasd12311");
        User user5 = new User(
                5,"Proto", "Alisa", "alisa.alco@mail.com", 14, "a1sd8er42");
        User user6 = new User(
                6,"xXAlexXx", "Alex", "alex@mail.com", 27, "X8832qwXX");
        User user7 = new User(
                7,"SaPsaN", "Mariy", "Mariy@mail.com", 22, "cc23664599");
        User user8 = new User(
               8, "mr.BROWer", "Pop", "popop.brown@mail.com", 34, "iq180");
        User user9 = new User(
               9, "Jili-Wik", "Mike", "mike.king@mail.com", 13, "123456789");
        User user10 = new User(
               10, "Elysy", "Elena", "elena.tomskaya@mail.com", 11, "555222333");

        users.put(user1.getUserName(), user1);
        users.put(user2.getUserName(), user2);
        users.put(user3.getUserName(), user3);
        users.put(user4.getUserName(), user4);
        users.put(user5.getUserName(), user5);
        users.put(user6.getUserName(), user6);
        users.put(user7.getUserName(), user7);
        users.put(user8.getUserName(), user8);
        users.put(user9.getUserName(), user9);
        users.put(user10.getUserName(), user10);
    }
}

