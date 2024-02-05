package com.start.mvc.repository.impl;

import com.start.mvc.entity.User;
import com.start.mvc.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository("userRepositoryByHibernete")
@RequiredArgsConstructor
public class UserRepositoryByHibernete implements UserRepository {
    private final SessionFactory sessionFactory;




    /**
     * Проверяет существует ли в базе пользователь стаким ником
     *
     * @param username ник пользователя
     * @return возвращает ответ true если в базе присутствует
     */
    @Override
    public boolean existsByUsername(String username) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Long> query = session.createQuery(
                        "SELECT COUNT(*) FROM User WHERE username = :nick", Long.class)
                .setParameter("nick", username);
        long count = query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return count != 0;
    }

    /**
     * Получение информации о пользователях постранично
     *
     * @param pageable информация для пагинации
     * @return возвращает список информации о пользователях
     */
    @Override
    public List<User> findAll(Pageable pageable) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> users = session.createQuery("select u from User u", User.class)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return users;
    }

    /**
     * Сохранение пользователя
     *
     * @param user информация о пользователе
     * @return возвращает ответ true если успешно сохранен в базу
     */
    @Override
    public boolean save(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.flush();
        session.getTransaction().commit();
        session.close();
        return true;
    }

    /**
     * Получение информации о пользователе с nickname
     *
     * @param username ник пользователя
     * @return возвращает информацию о пользователе
     */
    @Override
    public User findByUsername(String username) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.createQuery(
                        "select u from User u WHERE u.userName = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
        session.getTransaction().commit();
        session.close();
        return user;
    }




    /**
     * Обновляет информацию о пользователе
     *
     * @param user пользователь (entity)
     * @return возвращает ответ true если в базе успешно изменили пользователя
     */
    @Override
    public boolean update(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String nick = user.getUserName();
        User userDB = session.createQuery(
                        "SELECT u FROM User u WHERE u.userName =:username", User.class)
                .setParameter("username", nick).getSingleResult();
        userDB.setLastName(user.getLastName());
        userDB.setAge(user.getAge());
        userDB.setEmail(user.getEmail());
        userDB.setPassword(user.getPassword());
        session.getTransaction().commit();
        session.close();
        return true;
    }
    /**
     * Проверяет существует ли в базе пользователь стаким email
     *
     * @param email email пользователя
     * @return возвращает ответ true если в базе присутствует
     */
    @Override
    public boolean existsByEmail(String email) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Long> query = session.createQuery(
                        "SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class)
                .setParameter("email", email);
        long count = query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return count != 0;
    }


}
